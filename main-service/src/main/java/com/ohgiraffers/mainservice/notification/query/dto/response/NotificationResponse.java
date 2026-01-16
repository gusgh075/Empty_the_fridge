package com.ohgiraffers.recipeservice.notification.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NotificationResponse {
    List<NotificationDTO> notifications;
}
