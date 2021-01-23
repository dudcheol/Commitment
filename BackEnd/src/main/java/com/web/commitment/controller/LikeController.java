package com.web.commitment.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.LikeDao;
import com.web.commitment.dto.Like;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class LikeController {

	@Autowired
	LikeDao likeDao;
	
	@PostMapping("/like")
    @ApiOperation(value = "좋아요 누르기 & 취소")
    public String tag(@RequestBody Like like) {
		
		// 이미 좋아요가 눌러져 있는 상태면 삭제
		Optional<Like> isLike = likeDao.findByEmailAndSnsId(like.getEmail(), like.getSnsId());
		
		try {
			if(!isLike.isPresent())
				likeDao.save(like);
			else
				likeDao.deleteById(isLike.get().getId());
		   	return "success";
		
		} catch(Exception e) {
	    	e.printStackTrace();
	    	return "error";
	    }

    }
}
