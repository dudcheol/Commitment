package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dao.UserBadgeDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.User;
import com.web.commitment.dto.UserBadge;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class BadgeController {
	@Autowired
	UserBadgeDao userBadgeDao;
	@Autowired
	CommitDao commitDao;
	@Autowired
	FollowDao followDao;
	@Autowired
	UserDao userDao;
	@Autowired
	CommitController commitController;

	@GetMapping("/badge/cnt")
	@ApiOperation(value = "뱃지 갯수")
	public int badgeCnt(@RequestParam String email) {
		return userBadgeDao.countByEmail(email);
	}

	@GetMapping("/badge/list")
	@ApiOperation(value = "뱃지 리스트")
	public List<UserBadge> badgeList(@RequestParam String email) {
		return userBadgeDao.findAllUserbadgeByEmail(email);
	}

	@GetMapping("badge/check")
	@ApiOperation(value = "뱃지 달성 체크")
	public List<Map> badgeCheck(@RequestParam String email) {
		List<UserBadge> list = userBadgeDao.findAllUserbadgeByEmail(email);
		
		List<Map> gets=new ArrayList<Map>();
		Queue<String> badges = new LinkedList<String>();
		
		for (UserBadge b : list) {// 가지고있는 뱃지
			badges.add(b.getName());
		}

		if (commitDao.commitCnt(email) >= 1 && !badges.contains("first_commit")) {
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("first_commit");
			map.put("badge", "first_commit");
			map.put("msg", "처음으로 커밋을 하셨네요!! 커밋먼트와 함께 여행을 떠나요~~");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (followDao.followingCnt(email) >= 1 && !badges.contains("first_following")) {
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("first_following");
			map.put("badge", "first_following");
			map.put("msg", "처음으로 팔로잉을 하셨네요!!");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (commitController.commitCount(email, "national").size() == 1056 && !badges.contains("total_commit")) {
			
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("total_commit");
			map.put("badge", "total_commit");
			map.put("msg", "축하드려요!! 전국 커밋 완료!!!");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (userDao.getDokdoCommit(email) >= 1 && !badges.contains("dokdo")) {
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("dokdo");
			map.put("badge", "dokdo");
			map.put("msg", "처음으로  독도에 커밋하셨습니다!");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (userDao.getSeoulCommit(email) >= 1 && !badges.contains("seoul")) {
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("seoul");
			map.put("badge", "seoul");
			map.put("msg", "처음으로 서울에 커밋하셨습니다!");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (userDao.getGangwondoCommit(email) >= 1 && !badges.contains("gangwondo")) {
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("gangwondo");
			map.put("badge", "gangwondo");
			map.put("msg", "처음으로 강원도에 커밋하셨습니다!");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (userDao.getGwangjuCommit(email) >= 1 && !badges.contains("busan")) {
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("busan");
			map.put("badge", "busan");
			map.put("msg", "처음으로 부산에 커밋하셨습니다!");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (userDao.getGyeonggidoCommit(email) >= 1 && !badges.contains("gyeonggido")) {
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("gyeonggido");
			map.put("badge", "gyeonggido");
			map.put("msg", "처음으로 경기도에 커밋하셨습니다!");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (userDao.getUlsanCommit(email) >= 1 && !badges.contains("ulsan")) {
			UserBadge userbadge = new UserBadge();
			userbadge.setEmail(email);
			Map<String, String> map = new HashMap<String, String>();
			userbadge.setName("ulsan");
			map.put("badge", "ulsan");
			map.put("msg", "처음으로 울산에 커밋하셨습니다!");
			map.put("result", "yes");
			userBadgeDao.save(userbadge);
			gets.add(map);
		}
		if (gets.size() == 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "획득한 뱃지가 없습니다.");
			gets.add(map);
		}
		

		return gets;
	}
	@GetMapping("badge/commitrate")
	@ApiOperation(value = "전국 커밋 달성률")
	public Map<String, Double> totalRate(@RequestParam String email) {
		Map<String, Double> map = new HashMap<String, Double>();
		double rate=(double)commitController.commitCount(email, "national").size()/(double)277*100;
		map.put("totalCommit",Math.round(rate*10)/10.0);
		return map;
	}
	@GetMapping("badge/user")
	@ApiOperation(value = "대표 뱃지 설정")
	public String setmybadge(@RequestParam String email,@RequestParam String badge) {
		Optional<User> option=userDao.findByEmail(email);
		if(option.isPresent()) {
			User user=option.get();
			user.setBadge(badge);
			userDao.save(user);
			return "success";
		}
		return "이메일이 없습니다";
	}
	@GetMapping("badge/mybadge")
	@ApiOperation(value = "대표 뱃지 불러오기")
	public String mybadge(@RequestParam String email) {
		Optional<User> option=userDao.findByEmail(email);
		if(option.isPresent()) {
			User user=option.get();
			return user.getBadge();
		}
		return "이메일이 없습니다";
	}
}
