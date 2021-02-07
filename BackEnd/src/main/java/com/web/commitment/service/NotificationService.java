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

	private User getUser(String nickname) { // 닉네임으로 유저 찾기
		return userDao.findUserByNickname(nickname).orElseThrow(() -> new BaseException(ErrorCode.UNEXPECTED_USER));
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
	public void saveNoti(NotificationReqDto notificationReqDto, String nickname) {
		LocalDateTime curDateTime = LocalDateTime.now();
		String nowDate = curDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

	
		// 저장할 데이터
		NotificationSaveDto notificationSaveDto = new NotificationSaveDto(nowDate, notificationReqDto.getDataId(),
				nickname, notificationReqDto.getIsRead(), notificationReqDto.getType());

		String type = notificationReqDto.getType();

		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("noti"); // 최상위 root: noti
		String toId = "";

		Board board = null;

		if (type.equals("like") || type.equals("comment")) {
			board = getBoard(notificationReqDto.getDataId());
			toId = notificationReqDto.getTo();
			notificationSaveDto.setFrom(nickname);
		} else {
			toId = notificationReqDto.getTo();
		}

//		User fromUser = getUser(nickname); // follow: 좋아요 받은 사람, like: 좋아요 받은 사람의 아이디
//		User toUser = getUser(toId);
//
//		System.out.println("to: " + toUser.getEmail());
//		System.out.println("from: " + fromUser.getEmail());
		
		DatabaseReference notiRef = ref.child(toId); // 알림 받는 사람의 닉네임
		System.out.println("notiRef: " + notiRef);
		DatabaseReference nextNotiRef = notiRef.push(); // 다음 키값으로 푸시
		System.out.println("nextNotiRef: " + nextNotiRef);
		String postId = nextNotiRef.getKey(); // 현재 알람의 키값을 가져옴
		System.out.println("postId: " + postId);
		
		DatabaseReference saveNoti = notiRef.child(postId); // to의 아이디 값의 child node

		if (type.equals("follow")) { // 팔로우
            	saveNoti.setValueAsync(notificationSaveDto); // 정의된 경로(예: users/<user-id>/<username>)에 데이터를 쓰거나 대체합니다.
		
		} else if (type.equals("like")) { // 좋아요
			saveNoti.setValueAsync(notificationSaveDto);
			
		} else if (type.equals("comment")) { // 댓글
			String lastId = commentDao.findByLastComment();
			notificationSaveDto.setCommentId(lastId);
			saveNoti.setValueAsync(notificationSaveDto);
			
		} else if (type.equals("commit")) { // 실시간 커밋
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