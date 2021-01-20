package com.web.commitment.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.User;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	UserDao userDao;

	@PostMapping("/account/signup")
	@ApiOperation(value = "회원가입수정")
	@Transactional
	public User signup(@Valid @RequestBody User request) {
		User user = userDao.findUserByEmail(request.getEmail());// 수정

		if (user == null)// 가입
			user = request;
		else {
			user.setEmail(request.getEmail());
			user.setPass(request.getPass());
			user.setNickname(request.getNickname());
			user.setTel(request.getTel());
			user.setAge(request.getAge());
			user.setGender(request.getGender());
			user.setMystory(request.getMystory());
		}

		System.out.println(user);
		userDao.save(user);

		return user;
	}
}
