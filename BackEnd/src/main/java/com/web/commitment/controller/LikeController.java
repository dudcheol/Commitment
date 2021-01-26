package com.web.commitment.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String putLike(@RequestBody Like like) {
		
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
	
	@GetMapping("/like")
	@ApiOperation(value = "게시글 좋아요 목록 불러오기")
	public List<Like> likeList(@RequestParam String email){
	
		return likeDao.findAllByEmail(email); // snsId로 게시글 불러오는 것까
	}

	@GetMapping("/like/received")
	@ApiOperation(value = "좋아요 받은 개수")
	public int likeCount(@RequestParam String email){
	
		return likeDao.countByWriter(email);
	}
	
	@GetMapping("/like/give")
	@ApiOperation(value = "좋아요 한 개수")
	public int likeGive(@RequestParam String email){
	
		return likeDao.countByEmail(email);
	}
}
