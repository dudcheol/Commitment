package com.web.commitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.ProfileDao;
import com.web.commitment.dto.BasicResponse;
import com.web.commitment.dto.Follow;
import com.web.commitment.dto.FollowId;
import com.web.commitment.dto.User;

import io.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
public class ProfileController {
	@Autowired
	ProfileDao profiledao;
	
	@GetMapping("/profile/follow")
	@ApiOperation(value = "팔로우 하기")
	public Object follow(@RequestParam(required = true) final String from,@RequestParam(required = true) final String to) {
		FollowId followid=new FollowId(from,to);
		Follow follow=new Follow();
		follow.setFollowid(followid);
		profiledao.save(follow);
		
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
