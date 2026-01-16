package com.ohgiraffers.recipeservice.notification.query.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO {

    private Long notificationNo;
    private Long userNo;
    private Integer notificationTypeNo;
    private String notificationTypeName;
    private String notificationContent;
    private Boolean isChecked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
