package com.web.commitment.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.multipart.MultipartFile; 
import java.io.IOException;

import com.web.commitment.dto.Commit;
import com.web.commitment.dto.User;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class BoardController {

    @PostMapping("/sns")
    @ApiOperation(value = "게시글 작성")
    public String commit(@RequestParam(required = true) final String email, @RequestParam(required = true) final int commit_id) {
		return email;
        
    	// email, commit_id를 받아오면 게시글 작성 (이미지 업로드까지)
//		try {
//			Commit commit = new Commit();
//			commit.setEmail(user.getEmail());
//			commit.setLat(user.getLat());
//			commit.setLng(user.getLng());
//			commit.setOpen(open);
//			 
//	        int emailResult = userDao.countByEmail(user.getEmail());
//	        
//	        // 현재 위치에 이미 커밋한 정보가 있으면 넣지 않는다.
//	        Optional<Commit> getCommit = commitDao.findByLatAndLng(user.getLat(), user.getLng());
//	        System.out.println(getCommit);
//	        
//	        // 해당 lat, lng 값을 가진 user가 있으면 X
//	        if(emailResult != 0 && !getCommit.isPresent()) { 
//	        	System.out.println(user);
//	        	commitDao.save(commit);
//	        	return "success";
//	        } else {
//	        	//commit overlap is not allowed
//	        	return "fail";
//	        }
//		} catch(Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
    }
    
//    // 불러오기 open이 1인 것만
//    @GetMapping("/account/mypage")
//    @ApiOperation(value = "내 정보")
//    public Optional<User> mypage(@RequestParam(required = true) final String email) {
//    	
//		final BasicResponse result = new BasicResponse();
//		
//        Optional<User> userOpt = userDao.findByEmail(email);
//        System.out.println(userOpt);
//
//        return userOpt;
//    }
//    
//    
//    // 수정 (글, 이미지만 수정 가능, 작성 시간)
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
//    	
//    	return response;
//    }
//    
//    // 게시글 삭제
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
}
