package com.web.commitment.dto.Notification;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationReqDto {
    private String to;
    private String dataId;
    private Boolean isRead;
    private String type;

}

