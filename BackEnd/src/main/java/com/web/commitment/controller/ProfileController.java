package com.web.commitment.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.commitment.dao.ProfileDao;
import com.web.commitment.dao.S3Dao;
import com.web.commitment.dto.BasicResponse;
import com.web.commitment.dto.Follow;
import com.web.commitment.dto.FollowId;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class ProfileController {
	@Autowired
	private ProfileDao profiledao;
//	@Autowired
//	private AWSService awsService;
	@Autowired
	private S3Dao s3Uploader;

	@GetMapping("/profile/follow")
	@ApiOperation(value = "팔로우 하기")
	public Object follow(@RequestParam(required = true) final String from,
			@RequestParam(required = true) final String to) {
		FollowId followid = new FollowId(from, to);
		Follow follow = new Follow();
		follow.setFollowid(followid);
		profiledao.save(follow);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/profile/follower")
	@ApiOperation(value = "팔로워 리스트")
	public Object follow(@RequestParam(required = true) final String email) {

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(path = "/upload")
	public Map<String, String> uploadFile(@RequestParam(value = "file", required = false) MultipartFile files)
			throws IOException {
		System.out.println(files.getOriginalFilename());

		String s3Path = "";
		s3Uploader.upload(files, s3Path);
		Map<String, String> result = new HashMap<>();
		result.put("key", files.getOriginalFilename());
		return result;
	}

}
