package com.ohgiraffers.mainservice.notification.query.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.notification.query.dto.response.NotificationResponse;
import com.ohgiraffers.hw22thteamproject.notification.query.service.NotificationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NotificationQueryController {

    private final NotificationQueryService notificationQueryService;

    @GetMapping("/notification")
    public ResponseEntity<ApiResponse<Object>> getNotification(
            @CookieValue(name = "refreshToken") String refreshToken
    ){
        NotificationResponse response = this.notificationQueryService.getNotificationByUserNo(refreshToken);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
