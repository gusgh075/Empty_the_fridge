package com.ohgiraffers.recipeservice.notification.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class NotificationCreateRequest {

    private Long userNo;
    private Integer notificationTypeNo;
    private String notificationContent;
}
