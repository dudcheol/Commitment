package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public String commit(@Valid @RequestBody User user, @PathVariable int open) {
    
    // user를 받아오면 해당 user, lat, lng로 커밋 정보 저장
    try {
        Commit commit = new Commit();
        commit.setEmail(user.getEmail());
        commit.setLat(user.getLat());
        commit.setLng(user.getLng());
        commit.setOpen(open);
        
        Optional<Commit> getCommit=commitDao.commitCheck(user.getEmail(),user.getLat() , user.getLng());
        
        // 해당 lat, lng 값을 가진 user가 있으면 X
        if(!getCommit.isPresent()) { 
            System.out.println(user);
            commitDao.save(commit);
            return "success";
        } else {
            //commit overlap is not allowed
            return "fail";
        }
    } catch(Exception e) {
        e.printStackTrace();
        return "error";
    }
}

// 커밋 불러오는 방법
//    @GetMapping("/commit")
//    @ApiOperation(value = "유저의 커밋 정보 불러오기")
//    public List<Commit> commit(@RequestParam(required = true) final String email) {
//
//        if(userDao.countByEmail(email) != 0) {
//            return commitDao.findAllByEmail(email);
//        }
//        return null;
//    }

//    @GetMapping("/commit")
//    @ApiOperation(value = "유저의 커밋 정보 불러오기")
//    public Map<String, String> commit(@RequestParam(required = true) final String email) {
//
//        Map<String, String> map = new HashMap<>();
//        if(userDao.countByEmail(email) != 0) {
//            commitDao.findAllByEmail(email)
//                .forEach(commit -> {
//                    map.put("lat", commit.getLat());
//                    map.put("lng", commit.getLng());
//                });
//        }
//        return map;
//    }

    @GetMapping("/commit")
    @ApiOperation(value = "유저의 커밋 정보 불러오기")
    public List<String[]> commit(@RequestParam(required = true) final String email) {

        List<String[]> list = new ArrayList<>();
        if(userDao.countByEmail(email) != 0) {
            commitDao.findAllByEmail(email).forEach(commit -> list.add(new String[] {commit.getLat(), commit.getLng()}));
        }
        return list;
    }
}