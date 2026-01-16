package com.ohgiraffers.ingredientstockservice.notification.query.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.notification.query.dto.response.NotificationResponse;
import com.ohgiraffers.ingredientstockservice.notification.query.service.NotificationQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NotificationQueryController {

    private final NotificationQueryService notificationQueryService;

    @GetMapping("/notification")
    public ResponseEntity<ApiResponse<Object>> getNotification(
            @AuthenticationPrincipal String userNo
    ){
        NotificationResponse response = this.notificationQueryService.getNotificationByUserNo(Long.valueOf(userNo));
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
