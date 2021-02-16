package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.LikeDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Comment;
import com.web.commitment.dto.Like;
import com.web.commitment.dto.User;
import com.web.commitment.dto.Notification.NotificationReqDto;
import com.web.commitment.response.BoardDto;
import com.web.commitment.response.CommentBoardDto;
import com.web.commitment.response.CommitClearDto;
import com.web.commitment.response.LikeBoardDto;
import com.web.commitment.response.LikeDto;
import com.web.commitment.response.UserDto;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class LikeController {

	@Autowired
	LikeDao likeDao;
	@Autowired
	UserDao userDao;
	@Autowired
	BoardDao boardDao;
	
	@Autowired	
	FollowingBoardController followingBoardController;
	@Autowired
	NotificationController notificationController;
	
	@PostMapping("/like")
    @ApiOperation(value = "좋아요 누르기 & 취소")
    public String putLike(@RequestBody Like like) {
		
		// 이미 좋아요가 눌러져 있는 상태면 삭제
		Optional<Like> isLike = likeDao.findByEmailAndSnsId(like.getEmail(), like.getSnsId());
		
		try {
			if(!isLike.isPresent()) {
				likeDao.save(like);
				
				// 실시간 알림 저장
				NotificationReqDto request = new NotificationReqDto();
				User fromUser = userDao.findUserByEmail(like.getEmail());
				Optional<Board> toUser = boardDao.findById(like.getSnsId());
				request.setIsRead(false);
				request.setTo(toUser.get().getUser().getNickname());
				request.setType("like");
				request.setDataId(like.getSnsId());
				
				if(!notificationController.saveNotification(fromUser.getNickname(), request).equals("success"))
					return "fail save noti";
			} else {
				// 좋아요 취소 시 알림부터 삭제
				Optional<Board> toUser = boardDao.findById(like.getSnsId());
				notificationController.deleteCancelNotification("like", toUser.get().getUser().getNickname(), isLike.get().getId());

				likeDao.deleteById(isLike.get().getId());
			}
		   	return "success";
		
		} catch(Exception e) {
	    	e.printStackTrace();
	    	return "error";
	    }
    }
	
	@GetMapping("/like")
	@ApiOperation(value = "게시글 좋아요 목록 불러오기")
	public Page<LikeDto> likeList(@RequestParam String email, final Pageable pageable){
		Page<Like> likes = likeDao.findAllByEmailOrderByCreatedAtDesc(email, pageable);
		System.out.println(likes.getSize());
		List<LikeDto> likeDtos=new ArrayList<LikeDto>();
		
		for (Like likeorigin: likes) {
			LikeDto like=new LikeDto();
			BeanUtils.copyProperties(likeorigin, like);
			
			//board 최적화(commit내역 제거)
			Board origin=likeorigin.getBoard();
			BoardDto target = new BoardDto();
			CommitClearDto cleardto=new CommitClearDto();
			BeanUtils.copyProperties(origin.getCommit(), cleardto);
			BeanUtils.copyProperties(origin, target);
			target.setCommit(cleardto);
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(origin.getUser(), userDto);
			target.setUser(userDto);
			//userDto 저장
			target.setUser(userDto);
			
			if (origin.getLike() != null) {//좋아요가 존재한다면
				List<LikeBoardDto> likeBoards = new ArrayList<LikeBoardDto>();
				for (Like l : origin.getLike()) {
					LikeBoardDto likeDto = new LikeBoardDto();
					BeanUtils.copyProperties(l, likeDto);
					likeBoards.add(likeDto);
				}
				target.setLike(likeBoards);
			}
			
			if (origin.getComment() != null) {//댓글 있다면
				List<CommentBoardDto> comments = new ArrayList<CommentBoardDto>();
				for (Comment c : origin.getComment()) {
					CommentBoardDto commentDto = new CommentBoardDto();
					BeanUtils.copyProperties(c, commentDto);
					comments.add(commentDto);
				}
				target.setComment(comments);
			}
			like.setBoard(target);
			//boardDto 저장
			likeDtos.add(like);
		}
		return new PageImpl<LikeDto>(likeDtos, pageable, likes.getTotalElements());
	}

	@GetMapping("/like/totalreceived")
	@ApiOperation(value = "좋아요 총 받은 개수")
	public int likeTotal(@RequestParam String email){
	
		return likeDao.countByWriter(email);
	}

	@GetMapping("/like/received")
	@ApiOperation(value = "해당 게시글 좋아요 받은 개수")
	public int likeCount(@RequestParam int id){
		
		return likeDao.countBySnsId(id);
	}
	
	@GetMapping("/like/give")
	@ApiOperation(value = "좋아요 한 개수")
	public int likeGive(@RequestParam String email){
	
		return likeDao.countByEmail(email);
	}
}
