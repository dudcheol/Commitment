package com.web.commitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.CommitDao;
import com.web.commitment.dto.BasicResponse;
import com.web.commitment.dto.Commit;

import io.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
public class TimelineController {
	@Autowired
	CommitDao commitDao;
	
	@GetMapping("/timeline")
	@ApiOperation(value = "타임라인정보 불러오기")
	public Object timeline(@RequestParam(required = true) final String email) {
		//commit table(커밋날짜 필요) 과 sns테이블(해당커밋에 쓴글의 sns_id필요)과 image테이블필요....(sns_id로 image조회)
		List<Commit>li=commitDao.findAllByEmail(email);
		for (Commit c:li) {
			System.out.println(c);
		}
		return li;
	}
}
