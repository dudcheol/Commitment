package com.web.commitment.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.User;

import io.swagger.annotations.ApiOperation;

public class UserController {

	@Autowired
	UserDao userDao;
	
    /// 해시태그로 검색
    @GetMapping("/search/nickname")
    @ApiOperation(value = "닉네임으로 검색")
    public Collection<User> searchByNickname(@RequestParam String keyword) {
    	
    	return userDao.findByNicknameContainingIgnoreCase(keyword);
    }
    
    /// 이메일로 검색
    @GetMapping("/search/email")
    @ApiOperation(value = "이메일로 검색")
    public Collection<User> searchByEmail(@RequestParam String keyword) {
    	
    	return userDao.findByEmailContainingIgnoreCase(keyword);
    }
}
