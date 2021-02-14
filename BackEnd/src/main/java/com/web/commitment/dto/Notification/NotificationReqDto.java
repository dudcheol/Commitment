package com.web.commitment.dto.Notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationReqDto {
    private String to; // 알림 받는 사람 (type이 commit일 경우 all로 지정)
    private String dataId;
    private Boolean isRead;
    private String type;
}

