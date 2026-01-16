package com.ohgiraffers.recipeservice.notification.command.application.controller;

import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.notification.command.application.dto.request.NotificationCreateRequest;
import com.ohgiraffers.recipeservice.notification.command.application.service.NotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationCommandController {

    private final NotificationCommandService notificationCommandService;

    /* 알람 읽음 처리 */
    @GetMapping("/notification/{notification-no}")
    public ResponseEntity<ApiResponse<Void>> checkNotification(
            @PathVariable("notification-no") Long notificationNo
            ) {
        this.notificationCommandService.checkNotification(notificationNo);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /* 알림 일괄 생성 (Feign Client 호출용) */
    @PostMapping("/notifications")
    public ResponseEntity<ApiResponse<Void>> createNotifications(
            @RequestBody List<NotificationCreateRequest> requests
    ) {
        this.notificationCommandService.createNotifications(requests);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
