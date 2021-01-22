package com.web.commitment.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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
    			sns.setCreatedAt(LocalDateTime.now());
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
    
    
    @PutMapping("/sns")
    @ApiOperation(value = "게시글 수정")
    public Object update(@RequestBody Board sns) {
    	
    	// 여기서는 id 받아와야 함
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

    @DeleteMapping("/sns")
    @ApiOperation(value = "게시글 삭제")
    public int delete(@RequestParam String sns_id) {
    	
    	Optional<Board> sns = boardDao.findById(sns_id);
    	System.out.println(sns);
    	
    	try {	
    		boardDao.delete(sns.get());
    		return 0;

    	} catch (Exception e) {
    		return 1;
    	}
    }
    
    // 대소문자 구분 없이 검색! IgnoreCase
    
    /// 제목으로 검색
    @GetMapping("/sns/title")
    @ApiOperation(value = "제목으로 검색")
    public List<Board> searchByTitle(@RequestParam String keyword) {
    	
    	return boardDao.findByTitleContainingIgnoreCase(keyword);
    }
   
    /// 내용으로 검색
    @GetMapping("/sns/content")
    @ApiOperation(value = "제목 & 내용으로 검색")
    public Collection<Board> searchByContent(@RequestParam String keyword) {
    	
    	return boardDao.findByContentContainingIgnoreCase(keyword);
    }
    
    /// 제목 & 내용으로 검색
    @GetMapping("/sns/tnc")
    @ApiOperation(value = "제목 & 내용으로 검색")
    public Collection<Board> searchByTandC(@RequestParam String keyword) {
    	
    	return boardDao.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }
}
