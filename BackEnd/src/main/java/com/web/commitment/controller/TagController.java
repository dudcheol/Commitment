package com.web.commitment.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.commitment.dao.TagDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Tag;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class TagController {

	@Autowired
	TagDao tagDao;
	
	@PostMapping("/tag/{id}")
    @ApiOperation(value = "해시태그 작성 & 수정 & 삭제")
    public String tag(@PathVariable String id, @RequestBody Tag[] tag) {
		
		List<Tag> tags = tagDao.findAllBySnsId(id);
		System.out.println(tags.size());
		for (int i = 0; i < tags.size(); i++) {
			System.out.println(tags.get(i).getContent());
		}
		tagDao.deleteAll(tags);
		
		try { 
			for (int i = 0; i < tag.length; i++) {
				tagDao.save(tag[i]);
			}	 
		   	return "success";
		
		} catch(Exception e) {
	    	e.printStackTrace();
	    	return "error";
	    }

    }
    
    // 유저의 게시글 불러오기 open이 1인 것만
    @GetMapping("/tag/{id}")
    @ApiOperation(value = "해당 게시글의 tag 목록")
    public List<Tag> mypage(@PathVariable String id) {

        return tagDao.findBySnsId(id);
    }
    
    /// 해시태그로 검색
    @GetMapping("/search/tag")
    @ApiOperation(value = "해시태그로 검색")
    public Collection<Tag> searchByTag(@RequestParam String keyword) {
    	
    	return tagDao.findByContentContainingIgnoreCase(keyword);
    }
}
