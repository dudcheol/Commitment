package com.web.commitment.dto.Notification;

import com.web.commitment.dao.Notification;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationRequestDto {

    @ApiModelProperty(position = 1)
    private Long from;
    @ApiModelProperty(position = 2)
    private Long to;
    @ApiModelProperty(position = 3)
    private Long dataId;
    @ApiModelProperty(position = 4)
    private String type;

    public Notification toEntity() {
        return Notification.builder()
                .from(from)
                .type(type)
                .to(to)
                .dataId(dataId)
                .build();
    }

}

