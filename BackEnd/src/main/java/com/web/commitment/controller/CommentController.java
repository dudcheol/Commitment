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

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.CommentDao;
import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Comment;
import com.web.commitment.dto.Commit;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class CommentController {

	@Autowired
	CommentDao commentDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CommitDao commitDao;
	
	@PostMapping("/comment")
    @ApiOperation(value = "댓글 작성")
    public String commit(@RequestBody Comment comment) {

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
    
//    // 유저의 게시글 불러오기 open이 1인 것만
//    @GetMapping("/comment")
//    @ApiOperation(value = "해당 게시글의 댓글 목록")
//    public List<Board> mypage(@RequestParam String sns_id) {
//    	
//        List<Commit> commitList = (List<Commit>) commitDao.findByEmail(email);
//        
//        List<Board> postList = new ArrayList<>();
//		for (Commit commit : commitList) {
//        	postList.addAll(boardDao.findAllByCommitId(commit.getId()));
//        	System.out.println(postList);
//		}
//
//        return postList;
//    }
//    
//    
//    @PutMapping("/comment")
//    @ApiOperation(value = "게시글 수정")
//    public Object update(@RequestBody Board sns) {
//    	
//    	// 여기서는 id 받아와야 함
//    	// email, commit_id를 받아오면 게시글 작성 (이미지 업로드까지) XXX
//    	try { 
//    		int emailResult = userDao.countByEmail(sns.getEmail());
//    		
//    		if(emailResult != 0) { 
//    			boardDao.save(sns);
//    			return "success";
//    		} else {
//    			// email이 없음
//    			return "fail";
//    		}
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    		return "error";
//    	}
//    }
//
//    @DeleteMapping("/comment")
//    @ApiOperation(value = "게시글 삭제")
//    public int delete(@RequestParam String sns_id) {
//    	
//    	Optional<Board> sns = boardDao.findById(sns_id);
//    	System.out.println(sns);
//    	
//    	try {	
//    		boardDao.delete(sns.get());
//    		return 0;
//
//    	} catch (Exception e) {
//    		return 1;
//    	}
//    }
}
