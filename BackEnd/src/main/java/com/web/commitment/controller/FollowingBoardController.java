package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.FollowCommitMap;
import com.web.commitment.dto.Like;
import com.web.commitment.dto.User;
import com.web.commitment.response.BoardDto;
import com.web.commitment.response.LikeDto;
import com.web.commitment.response.UserDto;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class FollowingBoardController {
	@Autowired
	BoardDao boardDao;
	@Autowired
	FollowDao followDao;
	@Autowired
	UserDao userDao;

	@GetMapping("/sns/followingboard")
	@ApiOperation(value = "팔로잉 한 사람의 게시글만 불러오기")
	public Page<BoardDto> mypage(@RequestParam String email, Pageable pageable) {
		Page<Board> boards = boardDao.findBoardByEmailSort(email, pageable);
		List<BoardDto> boardDtos =clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());
	}

	@GetMapping("/sns/totalboard")
	@ApiOperation(value = "팔로잉 & 내가 쓴 게시글")
	public Page<BoardDto> totalboard(@RequestParam String email, Pageable pageable) {
		Page<Board> boards = boardDao.findtotalByEmail(email, pageable);
		List<BoardDto> boardDtos = clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());
	}
	//유저의 전체  커밋 내역이 게시물마다 계속 출력되어 최적화 작업..
	public List<BoardDto> clean(Page<Board> boards){
		List<BoardDto> boardDtos = new ArrayList<BoardDto>();

		for (Board origin : boards) {
			BoardDto target = new BoardDto();
			BeanUtils.copyProperties(origin, target);
			UserDto userDto = new UserDto(origin.getUser());
			
			if (origin.getLike() != null) {
				List<LikeDto> likes = new ArrayList<LikeDto>();
				for (Like l : origin.getLike()) {
					LikeDto likeDto = new LikeDto(l);
					likes.add(likeDto);
				}
				target.setLike(likes);
			}
			
			target.setUser(userDto);
			boardDtos.add(target);
		}
		return boardDtos;
	}
}
