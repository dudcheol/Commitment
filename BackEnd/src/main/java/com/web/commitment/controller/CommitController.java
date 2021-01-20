package com.web.commitment.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Commit;
import com.web.commitment.dto.User;
import com.web.commitment.model.BasicResponse;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class CommitController {

	@Autowired
	CommitDao commitDao;
	
	@Autowired
	UserDao userDao;
	
	// CRUD 중 C만
    @PostMapping("/commit/{open}")
    @ApiOperation(value = "커밋하기")
    public Object signup(@Valid @RequestBody User user, @PathVariable int open) {
        
    	// user를 받아오면 해당 user, lat, lng로 커밋 정보 저장
		final BasicResponse result = new BasicResponse();
		
		try {
			Commit commit = new Commit();
			commit.setEmail(user.getEmail());
			commit.setLat(user.getLat());
			commit.setLng(user.getLng());
			commit.setOpen(open);
			 
	        int emailResult = userDao.countByEmail(user.getEmail());
	
	        if(emailResult != 0) {
	        	System.out.println(user);
	        	commitDao.save(commit);
	        	result.status = true;
	        	result.data = "success";	
	        } else {
	        	result.status = true;
	        	result.data = "fail";
	        }
		} catch(Exception e) {
			e.printStackTrace();
			result.status = false;
			result.data = "fail";
		}
        
    	return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
    
	@GetMapping("/commit")
    @ApiOperation(value = "커밋 정보 불러오기")
    public Object login(@RequestParam(required = true) final String email) {
    	
		final BasicResponse result = new BasicResponse();
		
        Optional<User> userOpt = userDao.findUserByEmail(email);
        System.out.println(email);
        ResponseEntity response = null;
        
        

        return response;
    }
}
