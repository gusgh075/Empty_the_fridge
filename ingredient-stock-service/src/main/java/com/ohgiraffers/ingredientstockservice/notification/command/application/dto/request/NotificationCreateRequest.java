package com.ohgiraffers.ingredientstockservice.notification.command.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationCreateRequest {

    private Long userNo;
    private Integer notificationTypeNo;
    private String notificationContent;
}
