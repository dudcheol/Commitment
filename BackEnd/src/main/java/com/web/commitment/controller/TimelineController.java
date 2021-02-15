package com.web.commitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.CommitDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Commit;
import com.web.commitment.response.BoardDto;

import io.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
public class TimelineController {
	@Autowired
	CommitDao commitDao;
	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	FollowingBoardController followingBoardController;
	
	@GetMapping("/timeline")
	@ApiOperation(value = "타임라인정보 불러오기")
	public List<BoardDto> timeline(@RequestParam(required = true) final String email, final Pageable pageable) {
		//commit table(커밋날짜 필요) 과 sns테이블(해당커밋에 쓴글의 sns_id필요)과 image테이블필요....(sns_id로 image조회)
		//세개 테이블을 조인시켜서 내보내야한다.
		
//		List<Commit> li = commitDao.findAllByEmail(email);
//		for (Commit c: li) {
//			System.out.println(c);
//		}
		
		Page<Board> boards = boardDao.findAllByEmail(email, null);
		List<BoardDto> boardDtos = followingBoardController.clean(boards);		
		
		return boardDtos;

	}
}
