package com.web.commitment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.CommentDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Comment;
import com.web.commitment.dto.User;

import com.web.commitment.dto.Notification.NotificationReqDto;
import com.web.commitment.response.CommentDto;
import com.web.commitment.response.UserDto;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class CommentController {

	@Autowired
	CommentDao commentDao;

	@Autowired
	BoardDao boardDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	NotificationController notificationController;
	
	@PostMapping("/comment")
	@ApiOperation(value = "댓글 작성 & 수정")
	public String writeComment(@RequestBody Comment comment) throws IOException {

		// id가 있으면
		// 댓글은 여러 개 작성 가능
		try {
			if (!comment.getParent().equals("0")) { // 부모 댓글이 있으면
				Optional<Comment> parent = commentDao.findById(comment.getParent());
				System.out.println(parent.get().getDepth());
				comment.setDepth(parent.get().getDepth() + 1);
			}
			commentDao.save(comment);
			
			User fromUser = userDao.findUserByEmail(comment.getEmail());
			Optional<Board> toUser = boardDao.findById(comment.getSnsId());
			NotificationReqDto request = new NotificationReqDto();
			request.setTo(toUser.get().getUser().getNickname());
			request.setDataId(comment.getSnsId());
			request.setIsRead(false);
			request.setType("comment");
			
			if(notificationController.saveNotification(fromUser.getNickname(), request).equals("success"))
				return "success";
			return "fail save noti";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/comment")
	@ApiOperation(value = "해당 게시글의 댓글 목록")
	public Page<CommentDto> mypage(@RequestParam String sns_id, Pageable pageable) {

		List<Comment> commentList = commentDao.findBySnsId(sns_id);
		for (Comment comment : commentList) {
			
			// 부모 0인경우 제외
			if (comment.getParent().equals("0"))
				continue;
			
			// 만약 부모 댓글이 db에 없다면 삭제하기
			Optional<Comment> parent = commentDao.findById(comment.getParent());
			if (!parent.isPresent())
				commentDao.delete(comment);
		}

		Page<Comment> finalComments = commentDao.findBySnsIdOrderByCreatedAt(sns_id, pageable);
		List<CommentDto> comments = new ArrayList<CommentDto>();

		for (Comment origin : finalComments) {
			CommentDto target = new CommentDto();
			BeanUtils.copyProperties(origin, target);
			
			UserDto user = new UserDto();
			BeanUtils.copyProperties(origin.getUser(), user);
			target.setUser(user);
//			user.setNickname(origin.getUser().getNickname());
//			user.setProfile(origin.getUser().getProfile());
			target.setUser(user);
			comments.add(target);
		}

		return new PageImpl<CommentDto>(comments, pageable, finalComments.getTotalElements());
	}

	@DeleteMapping("/comment")
	@ApiOperation(value = "댓글 삭제")
	public int delete(@RequestParam String id) {

		Optional<Comment> comment = commentDao.findById(id);

		try {
			System.out.println(comment.isPresent());
			if (comment.isPresent()) {
				Optional<Board> toUser = boardDao.findById(comment.get().getSnsId());
				// 댓글 삭제시 알림 삭제
				notificationController.deleteCancelNotification("comment", toUser.get().getUser().getNickname(), id);
				
				commentDao.delete(comment.get());
			}

		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
