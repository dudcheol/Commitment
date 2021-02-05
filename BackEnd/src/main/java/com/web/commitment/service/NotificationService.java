package com.web.commitment.service;

import com.google.firebase.database.*;
import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.CommentDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dao.LikeDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.Comment;
import com.web.commitment.dto.User;
import com.web.commitment.dto.Notification.NotificationReqDto;
import com.web.commitment.dto.Notification.NotificationSaveDto;
import com.web.commitment.exception.BaseException;
import com.web.commitment.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {
	@Autowired
	private final UserDao userDao;
	private final FollowDao followDao;
	private final LikeDao likeDao;
	private final BoardDao boardDao;
	private final CommentDao commentDao;

	private User getUser(String email) {
		return userDao.findByEmail(email).orElseThrow(() -> new BaseException(ErrorCode.UNEXPECTED_USER));
	}

	private Board getBoard(String postId) {
		return boardDao.findById(postId).orElseThrow(() -> new BaseException(ErrorCode.UNEXPECTED_POST));
	}

	private void saveNotificationData(String fromUserEmail, DatabaseReference notiRef, String type) {
		notiRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				exFindData: for (DataSnapshot data : snapshot.getChildren()) {
					String postKey = data.getKey();
					for (DataSnapshot valueForFrom : data.getChildren()) {
						if (valueForFrom.getKey().equals("from")) {
							if (valueForFrom.getValue() == fromUserEmail) {
								for (DataSnapshot valueForType : data.getChildren()) {
									if (valueForType.getKey().equals("type")) {
										if (valueForType.getValue().equals(type)) {
											DatabaseReference deleteRef = notiRef.child(postKey);
											deleteRef.removeValueAsync();
											break exFindData;
										}
									}
								}
							}
						}
					}
				}
			}

			@Override
			public void onCancelled(DatabaseError error) {
			}
		});
	}

	@Transactional
	public void saveNoti(NotificationReqDto notificationReqDto, String userId) {
		LocalDateTime curDateTime = LocalDateTime.now();
		String nowDate = curDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

		// 저장할 데이터
		NotificationSaveDto notificationSaveDto = new NotificationSaveDto(nowDate, notificationReqDto.getDataId(),
				userId, notificationReqDto.getIsRead(), notificationReqDto.getType());

		String type = notificationReqDto.getType();

		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("noti"); // 최상위 root: noti
		String toId = "";

		Board board = null;

		if (type.equals("like") || type.equals("comment")) {
			board = getBoard(notificationReqDto.getDataId());
			toId = notificationReqDto.getTo();
		} else {
			toId = notificationReqDto.getTo();
		}

		User fromUser = getUser(userId);

		DatabaseReference notiRef = ref.child(toId.toString()); // 알림 받는 사람의 아이디
		DatabaseReference nextNotiRef = notiRef.push(); // 다음 키값으로 푸시
		String postId = nextNotiRef.getKey(); // 현재 알람의 키값을 가져옴
		DatabaseReference saveNoti = notiRef.child(postId); // to의 아이디 값의 child node

		if (type.equals("follow")) { // 팔로우
//            if (followDao.findByToUserAndFromUser(fromUser.getEmail(), toUser.getEmail()).isPresent()) {
//                saveNotificationData(userId, notiRef, type);
//            } else {
			saveNoti.setValueAsync(notificationSaveDto);
//            }
		} else if (type.equals("like")) { // 좋아요
			if (likeDao.findByEmailAndSnsId(fromUser.getEmail(), board.getId()).isPresent()) {
				saveNotificationData(userId, notiRef, type);
			} else {
				saveNoti.setValueAsync(notificationSaveDto);
			}
		} else if (type.equals("comment")) { // 댓글
			Comment comment = commentDao.findByLastComment();
			notificationSaveDto.setCommentId(comment.getId());
			saveNoti.setValueAsync(notificationSaveDto);
		} else if (type.equals("approve")) {
			saveNoti.setValueAsync(notificationSaveDto);
		} else {
			saveNoti.setValueAsync(notificationSaveDto);
		}
	}

	@Transactional
	public void readNoti(String notiId, String userId) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("noti"); // 최상위 root: noti
		DatabaseReference notiRef = ref.child(userId.toString()); // noti의 child node: to의 아이디 값
		DatabaseReference updateRef = notiRef.child(notiId);
		updateRef.child("isRead").setValueAsync(true);
	}

	@Transactional
	public void deleteNoti(String notiId, String userId) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("noti"); // 최상위 root: noti
		DatabaseReference notiRef = ref.child(userId.toString()); // noti의 child node: to의 아이디 값
		DatabaseReference deleteRef = notiRef.child(notiId);
		deleteRef.removeValueAsync();
	}

	@Transactional
	public void deleteCommentAlert(String commentId, String userId) {
		if (commentDao.findById(commentId).isPresent()) {
			final FirebaseDatabase database = FirebaseDatabase.getInstance();
			DatabaseReference ref = database.getReference("noti"); // 최상위 root: noti
			DatabaseReference notiRef = ref.child(userId.toString()); // noti의 child node: to의 아이디 값
			notiRef.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot snapshot) {
					exFindData: for (DataSnapshot data : snapshot.getChildren()) {
						String postKey = data.getKey();
						for (DataSnapshot value : data.getChildren()) {
							if (value.getKey().equals("commentId")) {
								if (value.getValue() == commentId) {
									DatabaseReference deleteRef = notiRef.child(postKey);
									deleteRef.removeValueAsync();
									break exFindData;
								}
							}
						}
					}
				}

				@Override
				public void onCancelled(DatabaseError error) {
				}
			});
		}
	}

}