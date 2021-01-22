package com.web.commitment.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

import com.web.commitment.dao.CommentDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Comment;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class CommentController {

	@Autowired
	CommentDao commentDao;
	
	@Autowired
	UserDao userDao;
	
	@PostMapping("/comment")
    @ApiOperation(value = "댓글 작성 & 수정")
    public String commit(@RequestBody Comment comment) {

		// id가 있으면 
		// 댓글은 여러 개 작성 가능
    	try { 
    		comment.setCreatedAt(LocalDateTime.now());
			commentDao.save(comment);
			return "success";
			
    	} catch(Exception e) {
    		e.printStackTrace();
    		return "error";
    	}
    }
    
    // 유저의 게시글 불러오기 open이 1인 것만
    @GetMapping("/comment")
    @ApiOperation(value = "해당 게시글의 댓글 목록")
    public List<Comment> mypage(@RequestParam String sns_id) {

        return commentDao.findBySnsId(sns_id);
    }

    @DeleteMapping("/comment")
    @ApiOperation(value = "댓글 삭제")
    public int delete(@RequestParam String id) {
    	
    	Optional<Comment> comment = commentDao.findById(id);

    	try {	
	    	if(comment.isPresent()) {
	    		commentDao.delete(comment.get());
	    	}

    	} catch (Exception e) {
    		return 0;
    	}
    	return 1;
    }
}
