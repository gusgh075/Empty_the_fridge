package com.ohgiraffers.notificationservice.notification.query.controller;

import com.ohgiraffers.notificationservice.common.dto.ApiResponse;
import com.ohgiraffers.notificationservice.notification.query.dto.response.NotificationResponse;
import com.ohgiraffers.notificationservice.notification.query.service.NotificationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "알림 조회", description = "알림 정보 조회 API")
@RestController
@RequiredArgsConstructor
public class NotificationQueryController {

    private final NotificationQueryService notificationQueryService;

    @Operation(
            summary = "사용자 알림 조회",
            description = "인증된 사용자의 모든 알림을 조회합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "알림 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않음")
    })
    @GetMapping("/notification")
    public ResponseEntity<ApiResponse<Object>> getNotification(
            @AuthenticationPrincipal String userNo
    ) {
        NotificationResponse response = this.notificationQueryService.getNotificationByUserNo(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
