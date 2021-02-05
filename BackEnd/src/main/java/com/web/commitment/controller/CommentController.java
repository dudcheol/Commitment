package com.web.commitment.controller;

import java.io.IOException;
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
//import com.web.commitment.dto.Token;

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
    public String writeComment(@RequestBody Comment comment) throws IOException {
		
//		NotificationController.Push(data, 0);
		
		// id가 있으면 
		// 댓글은 여러 개 작성 가능
    	try { 
    		if(!comment.getParent().equals("0")) { // 부모 댓글이 있으면
    			Optional<Comment> parent = commentDao.findById(comment.getParent());
    			System.out.println(parent.get().getDepth());
    			comment.setDepth(parent.get().getDepth() + 1);
    		}
			commentDao.save(comment);
			return "success";
			
    	} catch(Exception e) {
    		e.printStackTrace();
    		return "error";
    	}
    }
    
    @GetMapping("/comment")
    @ApiOperation(value = "해당 게시글의 댓글 목록")
    public List<Comment> mypage(@RequestParam String sns_id) {
    	
    	List<Comment> commentList = commentDao.findBySnsId(sns_id);
    	for (Comment comment : commentList) {
			// 만약 부모 댓글이 db에 없다면 삭제하기
    		Optional<Comment> parent = commentDao.findById(comment.getParent());
    		if(!parent.isPresent())
    			commentDao.delete(comment);
		}
    	
    	List<Comment> finalComments = commentDao.findBySnsId(sns_id);
        return finalComments;
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
