package com.web.commitment.controller;

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
import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Commit;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class BoardController {

	@Autowired
	BoardDao boardDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CommitDao commitDao;
	
	@PostMapping("/sns")
    @ApiOperation(value = "게시글 작성")
    public String commit(@RequestBody Board sns) {
    	
		// 여기서는 id 필요 없음
    	// email, commit_id를 받아오면 게시글 작성 (이미지 업로드까지) XXX
    	try { 
    		int emailResult = userDao.countByEmail(sns.getEmail());

    		if(emailResult != 0) { 
    			boardDao.save(sns);
    			return "success";
    		} else {
    			// email이 없음
    			return "fail";
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    		return "error";
    	}
    }
    
    // 유저의 게시글 불러오기 open이 1인 것만
    @GetMapping("/sns")
    @ApiOperation(value = "로그인 한 유저의 게시글 목록")
    public List<Board> mypage(@RequestParam String email) {
    	
        List<Commit> commitList = (List<Commit>) commitDao.findByEmail(email);
        
        List<Board> postList = new ArrayList<>();
		for (Commit commit : commitList) {
        	postList.addAll(boardDao.findAllByCommitId(commit.getId()));
        	System.out.println(postList);
		}

        return postList;
    }
    
//    @DeleteMapping("/account/delete")
//    @ApiOperation(value = "탈퇴하기")
//    public Object delete(@RequestParam String email) {
//    	
//    	final BasicResponse result = new BasicResponse();
//    	ResponseEntity response = null;
//    	
//    	Optional<User> user = userDao.findByEmail(email);
//    	System.out.println(user);
//    	
//    	try {	
//    		userDao.delete(user.get());
//    		
//            result.status = true;
//            result.data = "success";
//            response = new ResponseEntity<>(result, HttpStatus.OK);
//
//    	} catch (Exception e) {
//    		result.status = false;
//    		result.data = "fail";
//    		response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
//    	}
//
//        return response;
//    }
//    
//    @PutMapping("/account/update")
//    @ApiOperation(value = "수정하기")
//    public Object update(@RequestParam String email, @Valid @RequestBody User request) {
//    	
//    	final BasicResponse result = new BasicResponse();
//    	ResponseEntity response = null;
//    	
//    	Optional<User> user = userDao.findByEmail(email);
//    	System.out.println(user);
//    	
//    	User upUser = new User();
//    	upUser.setEmail(request.getEmail());
//    	upUser.setPassword(request.getPassword());
//    	upUser.setCreateDate(request.getCreateDate());
//    	upUser.setUid(request.getUid());
//    	
//    	System.out.println(user);
//    	
//    	try {	    		
//    		userDao.save(upUser);
//    		
//    		result.status = true;
//    		result.data = "success";
//    		response = new ResponseEntity<>(result, HttpStatus.OK);
//    		
//    	} catch (Exception e) {
//    		result.status = false;
//    		result.data = "fail";
//    		response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
//    	}
//    }
}
