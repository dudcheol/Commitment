package com.web.commitment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
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
import com.web.commitment.dto.Commit;
import com.web.commitment.dto.User;

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

	// CRUD 중 C만
	@PostMapping("/commit/{open}")
	@ApiOperation(value = "커밋하기")
	public String commit(@Valid @RequestBody User user, @PathVariable int open) {
		// user를 받아오면 해당 user, lat, lng로 커밋 정보 저장
		try {
			Commit commit = new Commit();
			commit.setEmail(user.getEmail());
			commit.setLat(user.getLat());
			commit.setLng(user.getLng());
			commit.setOpen(open);
			// 여기에 인덱스 변환 넣기
			String region=reverseGeo(user.getLat(),user.getLng());
			System.out.println(region);
			if(region.equals("서울")) {
				commit.setRegion_name("seoul");
			}else if(region.equals("경기")) {
				commit.setRegion_name("gyenggi");
			}else if(region.equals("강원")) {
				commit.setRegion_name("gangwon");
			}else if(region.equals("광주")) {
				commit.setRegion_name("gwangju");
			}else if(region.equals("울산")) {
				commit.setRegion_name("ulsan");
			}else if(region.equals("부산")) {
				commit.setRegion_name("busan");
			}else if(region.equals("fail")) {
				return "error";
			}else {
				commit.setRegion_name("national");
			}
			commitDao.save(commit);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@PostMapping("/commit/copy/{open}")
	@ApiOperation(value = "커밋하기") // 테스트 용
	public String commit2(@Valid @RequestBody Commit commit, @PathVariable int open) {
		// user를 받아오면 해당 user, lat, lng로 커밋 정보 저장
		try {
			commitDao.save(commit);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 커밋 불러오는 방법
//	@GetMapping("/commit")
//    @ApiOperation(value = "유저의 커밋 정보 불러오기")
//    public List<Commit> commit(@RequestParam(required = true) final String email) {
//		
//		if(userDao.countByEmail(email) != 0) {
//			return commitDao.findAllByEmail(email);
//		}
//		return null;
//    }

//	@GetMapping("/commit")
//    @ApiOperation(value = "유저의 커밋 정보 불러오기")
//    public Map<String, String> commit(@RequestParam(required = true) final String email) {
//		
//    	Map<String, String> map = new HashMap<>();
//		if(userDao.countByEmail(email) != 0) {
//			commitDao.findAllByEmail(email)
//				.forEach(commit -> {
//					map.put("lat", commit.getLat());
//					map.put("lng", commit.getLng());
//				});
//		}
//		return map;
//    }

	@GetMapping("/commit")
	@ApiOperation(value = "유저의 커밋 정보 불러오기")
	public List<String[]> commit(@RequestParam(required = true) final String email) {

		List<String[]> list = new ArrayList<>();
		if (userDao.countByEmail(email) != 0) {
			commitDao.findAllByEmail(email)
					.forEach(commit -> list.add(new String[] { commit.getLat(), commit.getLng() }));
		}
		return list;
	}

	@GetMapping("/commit/timeCheck")
	@ApiOperation(value = "커밋 시간제한 확인")
	public String timeCheck(@RequestParam(required = true) final String email, @RequestParam(required = true) int x,
			@RequestParam(required = true) int y, @RequestParam(required = true) String region) {
		// x,y는 위도 경도 변환된 인덱스
		List<Commit> li = commitDao.timeCheck(email, x, y, region);

		if (li.size() != 0)
			return "false";
		return "success";
	}
	@GetMapping("/test")
	//위도경도-> 지역 이름 (서울,광주,경기....)
	public String reverseGeo(@RequestParam(required = true) String lat, @RequestParam(required = true) String lng)
			throws ParseException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(
				"https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?output=json&orders=legalcode&request=coordsToaddr&coords="
						+ lat + "," + lng);
		System.out.println(
				"https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?output=json&orders=legalcode&request=coordsToaddr&coords="
						+ lat + "," + lng);
		getRequest.setHeader("X-NCP-APIGW-API-KEY-ID", "t6fd643dic");
		getRequest.setHeader("X-NCP-APIGW-API-KEY", "tNqHk0pfH4E9IR0cLqPmijdaFkCdtKt6782DUIkF");

		try {
			HttpResponse response = client.execute(getRequest);
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(body);

				JSONArray results = (JSONArray) obj.get("results");
				
				JSONObject obj2=(JSONObject) results.get(0);				
				JSONObject results2 = (JSONObject) obj2.get("region");
				JSONObject  results3= (JSONObject) results2.get("area1");
				return results3.get("alias").toString();
						
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "fail";

	}
}
