package com.web.commitment.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Comment;
import com.web.commitment.dto.Commit;
import com.web.commitment.dto.Like;
import com.web.commitment.dto.User;
import com.web.commitment.dto.Notification.NotificationReqDto;
import com.web.commitment.response.BoardCommitDto;
import com.web.commitment.response.BoardDto;
import com.web.commitment.response.CommentBoardDto;
import com.web.commitment.response.CommentCleanDto;
import com.web.commitment.response.CommitClearDto;
import com.web.commitment.response.CommitDto;
import com.web.commitment.response.FollowCommitMap;
import com.web.commitment.response.LikeBoardDto;
import com.web.commitment.response.LikeCleanDto;
import com.web.commitment.response.UserDto;

import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@CrossOrigin
@RestController
public class CommitController {

	@Autowired
	CommitDao commitDao;

	@Autowired
	UserDao userDao;
	
	@Autowired
	NotificationController notificationController;

	static final double METER_PER_LAT = 88740; // 경도 1도당 미터
	static final double METER_PER_LNG = 110000; // 위도 1도당 미터
	HashMap<String, double[]> hm = new HashMap<>();

	{
		// tile 사이즈,latmin,latmax,lngmin,mngmax
		double[] national = { 20000, 33.0, 38.9, 124.5, 132.0 };
		double[] seoul = { 1000, 37.413294, 37.715133, 126.734086, 127.269311 };
		double[] gyenggi = { 3000, 36.87226, 38.300603, 126.262021, 127.830532 };
		double[] busan = { 1200, 34.8799083, 35.3959361, 128.7384361, 129.3728194 };
		double[] gangwon = { 4000, 37.018205, 38.642618, 127.080231, 129.37191 };
		double[] gwangju = { 800, 35.0, 35.2, 126.6, 127 };
		double[] ulsan = { 1000, 35.3130416, 35.7376583, 128.9607861, 129.4840972 };
		hm.put("전국", national);
		hm.put("서울", seoul);
		hm.put("경기", gyenggi);
		hm.put("부산", busan);
		hm.put("강원", gangwon);
		hm.put("광주", gwangju);
		hm.put("울산", ulsan);
	}

	// CRUD 중 C만
	// 커밋 행렬 좌표, 횟수 불러오기
	@GetMapping("/commit")
	@ApiOperation(value = "인덱스 별 커밋 횟수 불러오기")
	public List<int[]> commitCount(@RequestParam(required = true) String email,
			@RequestParam(required = false) String name) {
		
		Map<Position, Integer> map = new HashMap<>();
		List<Commit> commits = new ArrayList<>();
		
		User user = userDao.findUserByEmail(email);
		if(user == null) {
			user = userDao.findByNickname(email);
			if(user != null)
				email = user.getEmail();
		}
		
		if (!name.equals("national")) {
			commits = commitDao.findAllByEmailAndRegion(email, name);

			for (Commit commit : commits) {
				map.put(new Position(Integer.parseInt(commit.getLocalX()), Integer.parseInt(commit.getLocalY())),
						map.getOrDefault(new Position(Integer.parseInt(commit.getLocalX()),
								Integer.parseInt(commit.getLocalY())), 0) + 1);
			}
		} else { // name이 null이라면 전국지도
			
			commits = commitDao.findAllByEmail(email);
			
			System.out.println(commits);
			for (Commit commit : commits) {
				map.put(new Position(Integer.parseInt(commit.getNationalX()), Integer.parseInt(commit.getNationalY())),
						map.getOrDefault(new Position(Integer.parseInt(commit.getNationalX()),
								Integer.parseInt(commit.getNationalY())), 0) + 1);
			}
		}

		List<int[]> positions = new ArrayList<>();
		// Iterator 사용 1 - keySet()
		Iterator<Position> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			Position key = keys.next();
			positions.add(new int[] { key.x, key.y, map.get(new Position(key.x, key.y)) });
			System.out.println("KEY : " + key.x + " " + key.y); //
		}

		return positions; // positions[0]: x좌표, positions[1]: y좌표, positions[2]: count list로
	}

	@GetMapping("commit/all")
	@ApiOperation(value = "회원전체 커밋 내역 지도 인덱스 보내주기")
	public List<int[]> allmap(@RequestParam String region) {
		Map<Position, Integer> map = new HashMap<>();

		List<Commit> commits;
		if (!region.equals("national")) {
			commits = commitDao.findAllByRegion(region);
			for (Commit commit : commits) {
				System.out.println(commit);
				
			}
		} else { // name이 null이라면 전국지도
			commits = commitDao.findAll();
		}
		for (Commit commit : commits) {
			map.put(new Position(Integer.parseInt(commit.getLocalX()), Integer.parseInt(commit.getLocalY())),
					map.getOrDefault(new Position(Integer.parseInt(commit.getLocalX()),
							Integer.parseInt(commit.getLocalY())), 0) + 1);
		}
		List<int[]> positions = new ArrayList<>();
		// Iterator 사용 1 - keySet()
		Iterator<Position> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			Position key = keys.next();
			positions.add(new int[] { key.x, key.y, map.get(new Position(key.x, key.y)) });
			System.out.println("KEY : " + key.x + " " + key.y); //
		}
		return positions;
	}

	// 네모칸 하나 눌렀을 때 네모칸 안의 커밋 정보 list
	@GetMapping("/commit/square")
	@ApiOperation(value = "네모칸 안의 커밋 정보 list")
	public List<CommitDto> commitSquare(@RequestParam String nickname, @RequestParam String x, @RequestParam String y,
			@RequestParam String region) {
		// region : 지역지도인지 국내지도인지/ 0: 국내지도, 1: 지역지도

		User isuser = userDao.findByNickname(nickname);
		String email = null;
		email = isuser.getEmail();
		System.out.println(email);
		
		List<Commit> commits = new ArrayList<>();
		List<CommitDto> commitDtos = new ArrayList<>();

		if (region.equals("national"))
			commits = commitDao.findAllByEmailAndNationalXAndNationalY(email, x, y);
		else
			commits = commitDao.findAllByEmailAndLocalXAndLocalYAndRegion(email, x, y, region);
	
		System.out.println(commits);
		for (Commit origin : commits) {
			CommitDto target = new CommitDto();
			BeanUtils.copyProperties(origin, target);
			
			List<BoardCommitDto> boards = new ArrayList<>();
			if(origin.getBoard() != null) {
				for (int i = 0; i < origin.getBoard().size(); i++) {
					BoardCommitDto board = new BoardCommitDto();
					BeanUtils.copyProperties(origin.getBoard().get(i), board);
					
					UserDto user = new UserDto();
					BeanUtils.copyProperties(origin.getBoard().get(i).getUser(), user);
					board.setUser(user);
					
					List<CommentCleanDto> comments = new ArrayList<>();
					for (int j = 0; j < origin.getBoard().get(i).getComment().size(); j++) {
						CommentCleanDto comment = new CommentCleanDto();
						BeanUtils.copyProperties(origin.getBoard().get(i).getComment().get(i), comment);
						comments.add(comment);
					}
					board.setComment(comments);
					
					List<LikeCleanDto> likes = new ArrayList<>();
					for (int j = 0; j < origin.getBoard().get(i).getLike().size(); j++) {
						LikeCleanDto like = new LikeCleanDto();
						BeanUtils.copyProperties(origin.getBoard().get(i).getLike().get(i), like);
						likes.add(like);
					}
					board.setLike(likes);
					
					boards.add(board);
				} 
			}
			
			target.setBoard(boards);
			commitDtos.add(target);
		}

		return commitDtos;
	}

	@GetMapping("/commit/total")
	@ApiOperation(value = "user의 총 커밋수")
	public int totalCommitNum(String email) {

		return commitDao.countByEmail(email);
	}

	// 커밋 불러오기 -> open 1인 것만

	// CRUD 중 C만
	@PostMapping("/commit/{open}")
	@ApiOperation(value = "커밋하기")
	public Commit commit(@Valid @RequestBody User user, @PathVariable int open) {
		// user를 받아오면 해당 user, lat, lng로 커밋 정보 저장
		//
		try {
			Commit commit = new Commit();
			commit.setEmail(user.getEmail());
			commit.setLat(user.getLat());
			commit.setLng(user.getLng());
			commit.setOpen(open);
			commit.setAddress(reverseGeoLocation(user.getLat(), user.getLng()));
			// 여기에 인덱스 변환 넣기
			String region = reverseGeo(user.getLat(), user.getLng());
			System.out.println(region);
			if (region.equals("서울")) {
				commit.setRegion("seoul");
			} else if (region.equals("경기")) {
				commit.setRegion("gyeonggi");
			} else if (region.equals("강원")) {
				commit.setRegion("gangwon");
			} else if (region.equals("광주")) {
				commit.setRegion("gwangju");
			} else if (region.equals("울산")) {
				commit.setRegion("ulsan");
			} else if (region.equals("부산")) {
				commit.setRegion("busan");
			} else if (region.equals("fail")) {
				return null;
			} else {
				commit.setRegion("national");
				region = "전국";
			}
			
			double[] arr = hm.get("전국");
			int[] dot = mapIndex(arr, user.getLat(), user.getLng());// 전국
			double[] local = hm.get(region);
			int[] dot2 = mapIndex(local, user.getLat(), user.getLng());
			
			commit.setNationalX(String.valueOf(dot[0]));
			commit.setNationalY(String.valueOf(dot[1]));
			commit.setLocalX(String.valueOf(dot2[0]));
			commit.setLocalY(String.valueOf(dot2[1]));
			System.out.println(commit);
			Commit c = commitDao.save(commit);

			
			// 실시간 알림 저장 -> open이 1일 때에만
			if(c.getOpen() == 1) {
				NotificationReqDto request = new NotificationReqDto();
				if(c.getAddress() != null)
					request.setDataId(c.getAddress()); // 주소
				else 
					request.setDataId("unknown");
				request.setIsRead(false);
				request.setTo("all");
				request.setType("commit");
				
				User fromUser = userDao.findUserByEmail(user.getEmail());
				notificationController.saveNotification(fromUser.getNickname(), request);
			}

			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private int[] mapIndex(double[] arr, String lat, String lng) {
		double tileSize = arr[0];
		double latmin = arr[1];
		double latmax = arr[2];
		double lngmin = arr[3];
		double lngmax = arr[4];
//		System.out.println(Arrays.toString(arr));

		double spacingR = tileSize * (1 / METER_PER_LAT);
		double spacingC = tileSize * (1 / METER_PER_LNG);
//		System.out.println("spacingC:"+spacingC+" spacingR:"+spacingR);
		int C = (int) ((latmax - latmin) / spacingC); // 위도 셀 개수 (세로)
		int R = (int) ((lngmax - lngmin) / spacingR); // 경도 셀 개수 (가로)
		System.out.println("C: " + C + " R: " + R);

		double targetLat = Double.parseDouble(lat);
		double targetLng = Double.parseDouble(lng);

		int x = (int) ((targetLat - latmin) / spacingC);
		int y = (int) ((targetLng - lngmin) / spacingR);
		x = (C - x >= C) ? C - 1 : C - x;
		y = (y >= R) ? R - 1 : y;
		System.out.println("X: " + x + " Y: " + y);
		return new int[] { x, y };
	}

	@GetMapping("/commit/user")
	@ApiOperation(value = "유저의 커밋 정보 불러오기")
	public List<String[]> commit(@RequestParam(required = true) final String email) {

		List<String[]> list = new ArrayList<>();
		if (userDao.countByEmail(email) != 0) {
			commitDao.findAllByEmail(email)
					.forEach(commit -> list.add(new String[] { commit.getLat(), commit.getLng() }));
		}
		return list;
	}

	@GetMapping("/commit/usermap")
	@ApiOperation(value = "유저의 대표지도 불러오기")
	public Map<String, Object> userMap(@RequestParam(required = true) final String email) {
		Optional<User> userOpt = userDao.findByEmail(email);
		Map<String, Object> map = new HashMap<String, Object>();

		if (userOpt.isPresent()) {
			User user = userOpt.get();
			String region = user.getRegion_name();
			List<int[]> list = commitCount(email, region);

			map.put("commitXY", list);
			map.put("region", region);
			map.put("data", "success");
			return map;
		}
		map.put("data", "없는 이메일 입니다.");
		return map;
	}

	@GetMapping("/commit/noboard")
	@ApiOperation(value = "빈커밋")
	public List<Commit> commitOnly(@RequestParam String email) {
		return commitDao.commitOnly(email);
	}

	// 위도경도-> 지역 이름 (서울,광주,경기....)
	public String reverseGeo(@RequestParam(required = true) String lat, @RequestParam(required = true) String lng)
			throws ParseException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(
				"https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?output=json&orders=legalcode&request=coordsToaddr&coords="
						+ lng + "," + lat);

		getRequest.setHeader("X-NCP-APIGW-API-KEY-ID", "");
		getRequest.setHeader("X-NCP-APIGW-API-KEY", "");

		try {
			HttpResponse response = client.execute(getRequest);
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(body);

				JSONArray results = (JSONArray) obj.get("results");

				JSONObject obj2 = (JSONObject) results.get(0);
				JSONObject results2 = (JSONObject) obj2.get("region");
				JSONObject results3 = (JSONObject) results2.get("area1");
				return results3.get("alias").toString();

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "fail";

	}

	@GetMapping("/commit/location")
	@ApiOperation(value = "위도 경도로 현재 위치 추출 ")
	public String reverseGeoLocation(@RequestParam(required = true) String lat,
			@RequestParam(required = true) String lng) throws ParseException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(
				"https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?output=json&orders=admcode&request=coordsToaddr&coords="
						+ lng + "," + lat);

		getRequest.setHeader("X-NCP-APIGW-API-KEY-ID", "");
		getRequest.setHeader("X-NCP-APIGW-API-KEY", "");

		try {
			HttpResponse response = client.execute(getRequest);
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(body);

				JSONArray results = (JSONArray) obj.get("results");

				JSONObject obj2 = (JSONObject) results.get(0);
				JSONObject results2 = (JSONObject) obj2.get("region");
//				
				JSONObject si = (JSONObject) results2.get("area1");
				String siname = (String) si.get("name");// 시
				JSONObject gu = (JSONObject) results2.get("area2");
				String guname = (String) gu.get("name");// 구군
				JSONObject dong = (JSONObject) results2.get("area3");
				String dongname = (String) dong.get("name");// 구군
				return siname + " " + guname + " " + dongname;
//				return results2;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "fail";
	}

	// 팔로우한 사람의 커밋지도 불러오기 (nickname, profile, 커밋지도 -> 최신순으로)
	@GetMapping("sns/followmap")
	@ApiOperation(value = "팔로우한 사람의 커밋지도 불러오기 (nickname, profile, 커밋지도 -> 최신순으로)")
	public List<FollowCommitMap> followmap(@RequestParam String email) {

		List<FollowCommitMap> result = new ArrayList<>();

		// 1. 팔로우한 사람 id목록 (최신순으로 불러오기)
		List<User> followings = userDao.findAllByFollowing(email);
		for (int i = 0; i < followings.size(); i++) {
			// 2. 각각 팔로우한 사람들에게서 커밋지도 불러오기
			FollowCommitMap followCommitMap = new FollowCommitMap();
			UserDto userdto = new UserDto();
			BeanUtils.copyProperties(followings.get(i), userdto);
			followCommitMap.setUser(userdto);
			
			System.out.println("followmap: " + followings.get(i).getEmail());
			System.out.println("followmap: " + followings.get(i).getRegion_name());
			followCommitMap.setCommit(commitCount(followings.get(i).getEmail(), followings.get(i).getRegion_name()));
			result.add(followCommitMap);
		}

		return result;
	}

	// 인덱스 정보
	static public class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj)// 비교 부분
		{
			return (y == ((Position) obj).y && x == ((Position) obj).x);
		}

		@Override
		public int hashCode()// 해쉬 코드 부분
		{
			return Integer.valueOf(y).hashCode() + Integer.valueOf(x).hashCode();
		}

	}	
}
