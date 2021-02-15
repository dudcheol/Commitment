package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
import com.web.commitment.dto.Notification.NotificationReqDto;
import com.web.commitment.response.UserDto;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class FollowController {
	@Autowired
	private FollowDao followDao;
	@Autowired
	private UserDao userDao;
	
	@Autowired
	NotificationController notificationController;

	@GetMapping("/profile/follow")
	@ApiOperation(value = "팔로우 하기&취소")
	public Object follow(@RequestParam(required = true) final String from,
			@RequestParam(required = true) final String to) {
		FollowId followid = new FollowId();
		followid.setFromUser(from);
		followid.setToUser(to);
		Follow follow = new Follow();
		follow.setFollowid(followid);
		Optional<Follow> option = followDao.findFollowByFromAndTo(from, to);

		if(option.isPresent()) {
			// 팔 취시 알림 삭제
			User toUser = userDao.getUserByEmail(to);
			System.out.println(option.get().getFollowid().getId());
			notificationController.deleteCancelNotification("follow", toUser.getNickname(), option.get().getFollowid().getId());
			
			followDao.delete(option.get());
		} else {
			followDao.save(follow);
			
			NotificationReqDto request = new NotificationReqDto();
			User toUser = userDao.getUserByEmail(to);
			User fromUser = userDao.getUserByEmail(from);
			request.setDataId(toUser.getNickname());
			request.setIsRead(false);
			request.setTo(toUser.getNickname());
			request.setType("follow");
			
			// 실시간 알림 저장
			notificationController.saveNotification(fromUser.getNickname(), request);
		}
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/profile/follower")
	@ApiOperation(value = "팔로워 리스트")
	public Object follower(@RequestParam(required = true) final String email) {
		List<Follow> list = followDao.FindFollowerListByEmail(email);
		List<UserDto> user = new ArrayList<UserDto>();
		for (Follow f : list) {
			UserDto userdto=new UserDto();
			BeanUtils.copyProperties(userDao.getUserByEmail(f.getFollowid().getFromUser()), userdto);
			user.add(userdto);
		}

		return user;
	}
	@GetMapping("/profile/following")
	@ApiOperation(value = "팔로잉 리스트")
	public Object following(@RequestParam(required = true) final String email) {
		List<Follow> list = followDao.FindFollowListByEmail(email);
		List<UserDto> user = new ArrayList<UserDto>();
		
		for (Follow f : list) {
			UserDto userdto=new UserDto();
			BeanUtils.copyProperties(userDao.getUserByEmail(f.getFollowid().getToUser()), userdto);
			user.add(userdto);
		}
		
		return user;
	}

	@GetMapping("/profile/followerCnt")
	@ApiOperation(value = "팔로워 수")
	public int followCnt(@RequestParam(required = true) String email) {
		return followDao.followCnt(email);
	}
}
