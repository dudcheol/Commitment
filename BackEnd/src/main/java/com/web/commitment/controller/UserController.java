package com.web.commitment.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.BasicResponse;
import com.web.commitment.dto.User;
import com.web.commitment.service.JwtService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	UserDao userDao;

	@Autowired
	private JwtService jwtService;

	@GetMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String pass) {
		Optional<User> userOpt = userDao.findUserByEmailAndPass(email, pass);
		ResponseEntity response = null;
		Map<String, Object> resultMap = new HashMap<>();

		if (userOpt.isPresent()) {
//        	jwt.io에서 확인
//			로그인 성공했다면 토큰을 생성한다.
			User user = userOpt.get();
			String token = jwtService.create(user);
//			logger.trace("로그인 토큰정보 : {}", token);

			resultMap.put("auth-token", token);
			resultMap.put("data", "success");
			resultMap.put("email", user.getEmail());
			resultMap.put("pass", user.getPass());
			resultMap.put("nickname", user.getNickname());
			resultMap.put("tel", user.getTel());
			resultMap.put("age", user.getAge());
			resultMap.put("gender", user.getGender());
			resultMap.put("mystory", user.getMystory());
			System.out.println("su");
			response = new ResponseEntity<>(resultMap, HttpStatus.OK);
		} else {
			resultMap.put("data", "fail");
			System.out.println("f");
			response = new ResponseEntity<>(resultMap, HttpStatus.OK);
		}

		return response;
	}

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

	@GetMapping("/account/info")
	@ApiOperation(value = "회원정보불러오기")
	public Object user(@RequestParam(required = true) final String email) {
		User user = userDao.getUserByEmail(email);
		return user;
	}

	@DeleteMapping("/account/delete")
	@ApiOperation(value = "회원탈퇴")
	@Transactional
	public Object userDelete(@RequestParam(required = true) final String email) {
		User user = userDao.getUserByEmail(email);
		userDao.delete(user);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/account/uidCheck")
	@ApiOperation(value = "닉네임 중복체크")
	public Map<String, String> uidCheck(@RequestParam(required = true) final String nickname) throws IOException {
		Optional<User> user = userDao.findUserByNickname(nickname);
		Map<String, String> hm = new HashMap<>();

		if (user.isPresent()) {
			hm.put("data", "fail");
			return hm;
		}
		hm.put("data", "success");
		return hm;
	}

}
