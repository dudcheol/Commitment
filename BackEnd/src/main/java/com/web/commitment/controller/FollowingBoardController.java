package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Commit;
import com.web.commitment.dto.Follow;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class FollowingBoardController {
	@Autowired
	BoardDao boardDao;
	@Autowired
	FollowDao followDao;
	
    @GetMapping("/sns/followingboard")
    @ApiOperation(value = "팔로잉 한 사람의 게시글만 불러오기")
    public List<Board> mypage(@RequestParam String email) {
    	
        List<Follow> followList =  followDao.FindFollowByEmail(email);
        
        List<Board> postList = new ArrayList<>();
        
		for (Follow f:followList) {
			List<Board> li=boardDao.findBoardByEmail(f.getFollowid().getTo());
			for (Board b: li) {
				postList.add(b);
			}
		}

        return postList;
    }

}
