package com.web.commitment.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.multipart.MultipartFile; 
import java.io.IOException;
import java.time.LocalDateTime;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Commit;
import com.web.commitment.dto.User;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class BoardController {

	@Autowired
	BoardDao boardDao;
	
	@Autowired
	UserDao userDao;
	
//    @PostMapping("/sns")
//    @ApiOperation(value = "게시글 작성")
//    public String commit(@Valid @RequestBody Board sns) {
//		
//    	// email, commit_id를 받아오면 게시글 작성 (이미지 업로드까지) XXX
//		try { 
//	        int emailResult = userDao.countByEmail(sns.getEmail());
//	        
//	        // 해당 lat, lng 값을 가진 user가 있으면 X
//	        if(emailResult != 0) { 
//	        	System.out.println(sns);
//	        	boardDao.save(sns);
//	        	return "success";
//	        } else {
//	        	
//	        	// email이 없음
//	        	return "fail";
//	        }
//		} catch(Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//    }

//	@Getter
//	@Setter
//	public class UploadBoardAndImages {
//		private MultipartFile images;
//		private Board board;
//	}
	
	@PostMapping("/sns")
    @ApiOperation(value = "게시글 작성")
    public String commit(@RequestBody Board sns) {
    	
    	// email, commit_id를 받아오면 게시글 작성 (이미지 업로드까지) XXX
    	try { 
    		int emailResult = userDao.countByEmail(sns.getEmail());
    		
    		// 해당 lat, lng 값을 가진 user가 있으면 X
    		if(emailResult != 0) { 
    			Board newSns = new Board();
    			
    			newSns.setCommitId(sns.getCommitId());
    			newSns.setEmail(sns.getEmail());
    			newSns.setTitle(sns.getTitle());
    			newSns.setContent(sns.getContent());
    			newSns.setLocation(sns.getLocation());
    			boardDao.save(newSns);
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
