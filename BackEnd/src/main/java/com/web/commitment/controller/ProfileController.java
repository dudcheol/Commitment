package com.web.commitment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.commitment.dao.ProfileDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dao.S3Dao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.BasicResponse;
import com.web.commitment.dto.Follow;
import com.web.commitment.dto.FollowId;
import com.web.commitment.dto.Profile;
import com.web.commitment.dto.User;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class ProfileController {

	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private S3Dao s3Uploader;

	@PostMapping(path = "/profile/upload")
	@ApiOperation(value = "프로필 업로드 및 수정")
	@Transactional
	public Map<String, String> uploadFile(@RequestParam(value = "file", required = true) MultipartFile files,
			@RequestParam(value = "email", required = true) String email) throws IOException {
		
		System.out.println(files.getSize());
		String originalFilename = files.getOriginalFilename();
		String saveFileName = UUID.randomUUID().toString()
				+ originalFilename.substring(originalFilename.lastIndexOf('.'));

		String s3Path = "profile";
		String url = s3Uploader.upload(files, s3Path, saveFileName);// 사진 업로드

		Profile profile = profileDao.findProfileByEmail(email);// 이미 프로필 사진이 존재하는가?
		if (profile == null) {
			profile = new Profile();
			profile.setEmail(email);
		} else {// 원래 프로필 사진 지우기
			s3Uploader.deletefile(profile.getFileName());
		}

		profile.setFileName(saveFileName);
		profile.setFilePath(url);

		System.out.println(profile);
		profileDao.save(profile);
		Map<String, String> result = new HashMap<>();
		result.put("url", profile.getFilePath());// 사진 url 리턴
		return result;
	}

//	@ApiOperation(value = "프로필 사진 불러오기")
//	public String image(String email) {
//		Profile profile = profileDao.findProfileByEmail(email);
//		return profile.getFile_path();
//	}

}
