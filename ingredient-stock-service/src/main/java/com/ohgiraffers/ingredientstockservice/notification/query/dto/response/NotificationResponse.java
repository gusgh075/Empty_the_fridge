package com.ohgiraffers.ingredientstockservice.notification.query.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationResponse {
    List<NotificationDTO> notifications;
}
