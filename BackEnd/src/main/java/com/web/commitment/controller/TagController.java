package com.web.commitment.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

import com.web.commitment.dao.TagDao;
import com.web.commitment.dto.Tag;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class TagController {

	@Autowired
	TagDao tagDao;
	
	@PostMapping("/tag")
    @ApiOperation(value = "해시태그 작성")
    public String tag(@RequestBody Tag[] tag) {
		try { 
			for (int i = 0; i < tag.length; i++) {
				tagDao.save(tag[i]);
			}	 
		   	return "success";
		
		} catch(Exception e) {
	    	e.printStackTrace();
	    	return "error";
	    }

    }
    
//    // 유저의 게시글 불러오기 open이 1인 것만
//    @GetMapping("/tag")
//    @ApiOperation(value = "해당 게시글의 댓글 목록")
//    public List<Tag> mypage(@RequestParam String sns_id) {
//
//        return tagDao.findBySnsId(sns_id);
//    }
//
//    @DeleteMapping("/tag")
//    @ApiOperation(value = "댓글 삭제")
//    public int delete(@RequestParam String id) {
//    	
//    	Optional<Comment> comment = commentDao.findById(id);
//
//    	try {	
//	    	if(comment.isPresent()) {
//	    		commentDao.delete(comment.get());
//	    	}
//
//    	} catch (Exception e) {
//    		return 0;
//    	}
//    	return 1;
//    }
}
