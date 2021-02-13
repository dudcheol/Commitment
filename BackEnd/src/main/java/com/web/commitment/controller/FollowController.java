package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.FollowDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.BasicResponse;
import com.web.commitment.dto.Follow;
import com.web.commitment.dto.FollowId;
import com.web.commitment.dto.User;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class FollowController {
	@Autowired
	private FollowDao followDao;
	@Autowired
	private UserDao userDao;


	@GetMapping("/profile/follow")
	@ApiOperation(value = "팔로우 하기&취소")
	public Object follow(@RequestParam(required = true) final String from,
			@RequestParam(required = true) final String to) {
		FollowId followid = new FollowId();
		followid.setFromUser(from);
		followid.setToUser(to);
		Follow follow = new Follow();
		follow.setFollowid(followid);
		Optional<Follow> option=followDao.findFollowByFromAndTo(from,to);
//		System.out.println(option.get());
		if(option.isPresent()) 
			followDao.delete(option.get());
		else
			followDao.save(follow);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/profile/follower")
	@ApiOperation(value = "팔로워 리스트")
	public Object follow(@RequestParam(required = true) final String email) {
		List<Follow> list = followDao.FindFollowByEmail(email);
		List<User> user = new ArrayList<User>();
		int index = 0;
		for (Follow f : list) {
			user.add(userDao.getUserByEmail(f.getFollowid().getToUser()));
			System.out.println(user.get(index++));
		}

		return user;
	}

	@GetMapping("/profile/followerCnt")
	@ApiOperation(value = "팔로워 수")
	public int followCnt(@RequestParam(required = true) String email) {
		return followDao.followCnt(email);
	}
}
