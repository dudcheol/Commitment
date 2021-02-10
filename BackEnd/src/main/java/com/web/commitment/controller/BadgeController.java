package com.web.commitment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.BadgeDao;
import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Badge;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class BadgeController {
	@Autowired
	BadgeDao badgedao;
	@Autowired
	CommitDao commitDao;
	@Autowired
	FollowDao followDao;
	@Autowired
	UserDao userDao;

	@GetMapping("/badge/cnt")
	@ApiOperation(value = "뱃지 갯수")
	public int badgeCnt(@RequestParam String email) {
		int count = 0;
		Badge b = badgedao.badgeCnt();
		count += b.getFirstCommit() + b.getDokdo() + b.getFirstLocation() + b.getFirstRanking() + b.getGangwondo()
				+ b.getGwanju() + b.getGyenggido() + b.getHiddenCommit() + b.getNumFollower() + b.getNumFollowing()
				+ b.getSecondRanking() + b.getSeoul() + b.getThirdRanking() + b.getTotalCommit() + b.getUlssan();
				
		return count;
	}
	@GetMapping("/badge/reset")
	@ApiOperation(value = "뱃지 초기화(회원 가입시)")
	public String badgeReset(@RequestParam String email) {
		Badge b=new Badge();
		b.setUserEmail(email);
		badgedao.save(b);
		return "success";
	}
	
	@GetMapping("/badge/list")
	@ApiOperation(value = "뱃지 리스트")
	public Badge badgeList(@RequestParam String email) {
		Badge b=badgedao.findBadgeByUserEmail(email);
		return b;
	}
	
	//뱃지달성을 어떻게 받아올건지는 프론트와 협의후 적절히 수정예정
	//객체로받아올 것인가 아니면 뱃지별로 메소드를 제작할것인가...?
	@PostMapping("/badge/list")
	@ApiOperation(value = "뱃지 저장")
	public String badgeList(@RequestBody Badge b) {
		badgedao.save(b);
		return "success";
	}
	
	
	@GetMapping("badge/check")
	@ApiOperation(value = "뱃지 달성 체크")
	public Map<String, String> badgeCheck(@RequestParam String email) {
		Badge b=badgedao.findBadgeByUserEmail(email);
		Map<String,String> map=new HashMap<String,String>();
	    
	    if(commitDao.commitCnt(email)==1&&b.getFirstCommit()==0) {
	        b.setFirstCommit(1);
	        map.put("badge","첫번째 커밋");
	        map.put("msg","처음으로 커밋을 하셨네요!! 커밋먼트와 함께 여행을 떠나요~~");
	        map.put("result", "yes");
	    }else if(followDao.followingCnt(email)==1&&b.getNumFollowing()==0) {
	        b.setNumFollowing(1);
	        map.put("badge","첫번째 팔로잉");
	        map.put("msg","처음으로 팔로잉을 하셨네요!!");
	        map.put("result", "yes");
	    }else if(commitController.commitCount(email,"national").size()==1056){
	        b.setTotalCommit(1);
	        badgedao.save(b);
	        map.put("badge","전국 커밋");
	        map.put("msg","축하드려요!! 전국 커밋 완료!!!");
	        map.put("result", "yes");
	    } else if(userDao.getDokdoCommit(email) == 1 && b.getFirstLocation() == 0) {
	    	b.setFirstLocation(1);
			badgedao.save(b);
			
			map.put("badge", "첫 번째로 독도에 커밋하셨습니다!");
			map.put("msg", "첫 번째 커밋: 독도");
			map.put("result", "yes");
		} else if(userDao.getSeoulCommit(email) >= 1 && b.getSeoul() == 0) {
			b.setSeoul(1);
			badgedao.save(b);
			
			map.put("badge", "첫 번째로 독도에 커밋하셨습니다!");
			map.put("msg", "첫 번째 커밋: 독도");
			map.put("result", "yes");
		} else if(userDao.getGangwondoCommit(email) >= 1 && b.getGangwondo() == 0) {
			b.setGangwondo(1);
			badgedao.save(b);
			
			map.put("badge", "첫 번째로 강원도에 커밋하셨습니다!");
			map.put("msg", "첫 번째 커밋: 강원도");
			map.put("result", "yes");
		} else if(userDao.getGwangjuCommit(email) >= 1 && b.getGwanju() == 0) {
			b.setGwanju(1);
			badgedao.save(b);
			
			map.put("badge", "첫 번째로 광주에 커밋하셨습니다!");
			map.put("msg", "첫 번째 커밋: 광주");
			map.put("result", "yes");
		} else if(userDao.getGyeonggidoCommit(email) >= 1 && b.getGyenggido() == 0) {
				b.setGyenggido(1);
				badgedao.save(b);
				
				map.put("badge", "첫 번째로 경기도에 커밋하셨습니다!");
				map.put("msg", "첫 번째 커밋: 경기도");
				map.put("result", "yes");
		} else if(userDao.getUlsanCommit(email) >= 1 && b.getUlssan() == 0) {
				b.setUlssan(1);
				badgedao.save(b);
				
				map.put("badge", "첫 번째로 울산에 커밋하셨습니다!");
				map.put("msg", "첫 번째 커밋: 울산");
				map.put("result", "yes");
		} else
			map.put("result", "no");
	    
	    return map;
	}
}
