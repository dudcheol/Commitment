package com.web.commitment.dto.Notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationSaveDto {
    private String from; // follow: 팔로우 누른 사람의 닉네임
    private String createdAt;
    private String dataId; // follow: 팔로우 받은 사람의 닉네임, like: 좋아요 받은 sns_id, comment: 댓글 받은 sns_id, commit: 주소
    private Boolean isRead;
    private String type;
    private String commentId; // comment: 방금 단 댓글의 id (알림 삭제 위함)
    private String likeId; // like: 방금 한 좋아요 id
    private String followId; // follow: 방금 신청한 follow id
    private String userEmail;
    private String profile; // 알림을 보낸 사람의 프로필 사진

    public NotificationSaveDto(String createdAt, String dataId, String fromUserNickname, Boolean isRead, String type){
        this.createdAt = createdAt;
        this.dataId = dataId;
        this.from = fromUserNickname;
        this.isRead = isRead;
        this.type = type;
    }

    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    
    public void setLikeId(String likeId){
    	this.likeId = likeId;
    }
    
    public void setFollowId(String followId){
    	this.followId = followId;
    }
    
    public void setUserEmail(String userEmail){
    	this.userEmail = userEmail;
    }
}

