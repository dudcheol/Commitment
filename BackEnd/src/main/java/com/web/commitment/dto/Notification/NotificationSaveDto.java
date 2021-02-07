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
    private String commentId; // 방금 단 댓글의 id


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

}

