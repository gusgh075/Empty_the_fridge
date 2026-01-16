package com.ohgiraffers.ingredientstockservice.notification.query.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
