package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Follow;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class FollowingBoardController {
	@Autowired
	BoardDao boardDao;
	@Autowired
	FollowDao followDao;
	
    @GetMapping("/sns/followingboard")
    @ApiOperation(value = "팔로잉 한 사람의 게시글만 불러오기")
    public Page<Board> mypage(@RequestParam String email,Pageable pageable) {

        return boardDao.findBoardByEmailSort(email,pageable);
    }
    @GetMapping("/sns/totalboard")
    @ApiOperation(value = "팔로잉 & 내가 쓴 게시글")
    public Page<Board> totalboard(@RequestParam String email,Pageable pageable) {
    	return boardDao.findtotalByEmail(email,pageable);
    }

}
