package com.ohgiraffers.ingredientstockservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateRequest {

    private Long userNo;
    private Integer notificationTypeNo;
    private String notificationContent;

}
