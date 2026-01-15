package com.ohgiraffers.mainservice.notification.command.application.controller;

import com.ohgiraffers.mainservice.common.dto.ApiResponse;
import com.ohgiraffers.mainservice.notification.command.application.service.NotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NotificationCommandController {

    private final NotificationCommandService notificationCommandService;

    /* 알람 읽음 처리 */
    @GetMapping("/notification/{notification-no}")
    public ResponseEntity<ApiResponse<Void>> checkNotification(
            @PathVariable("notification-no") Long notificationNo
            ) {
        this.notificationCommandService.checkNotification(notificationNo);
        return null;
    }

}
