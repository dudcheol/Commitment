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
import com.web.commitment.dto.Comment;
import com.web.commitment.dto.Like;
import com.web.commitment.response.BoardDto;
import com.web.commitment.response.CommentBoardDto;
import com.web.commitment.response.CommitClearDto;
import com.web.commitment.response.LikeBoardDto;
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
			//같은 속성 복사. 다른속성이 있을경우에는 따로 set해줘야함
			BeanUtils.copyProperties(origin, target);

			//commit내역제외한 user 저장
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(origin.getUser(), userDto);
			target.setUser(userDto);
			
			if (origin.getLike() != null) {
				List<LikeBoardDto> likes = new ArrayList<LikeBoardDto>();
				for (Like l : origin.getLike()) {
					LikeBoardDto likeDto = new LikeBoardDto();
					BeanUtils.copyProperties(l, likeDto);
					likes.add(likeDto);
				}
				target.setLike(likes);
			}
			if (origin.getComment() != null) {
				List<CommentBoardDto> comments = new ArrayList<CommentBoardDto>();
				for (Comment c : origin.getComment()) {
					CommentBoardDto commentDto = new CommentBoardDto();
					BeanUtils.copyProperties(c, commentDto);
					comments.add(commentDto);
				}
				target.setComment(comments);
			}
			if(origin.getCommit() != null) {
				CommitClearDto commit = new CommitClearDto();
				BeanUtils.copyProperties(origin.getCommit(), commit);
				target.setCommit(commit);
			}
			
			boardDtos.add(target);
		}
		return boardDtos;
	}
}
