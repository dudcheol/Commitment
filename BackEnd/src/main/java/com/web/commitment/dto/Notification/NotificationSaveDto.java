package com.web.commitment.dto.Notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationSaveDto {
    private String from;
    private String createdAt;
    private String dataId;
    private Boolean isRead;
    private String type;
    private String commentId;


    public NotificationSaveDto(String createdAt, String dataId, String fromUserEmail, Boolean isRead, String type){
        this.createdAt = createdAt;
        this.dataId = dataId;
        this.from = fromUserEmail;
        this.isRead = isRead;
        this.type = type;
    }

    public void setCommentId(String commentId){
        this.commentId = commentId;
    }

}

