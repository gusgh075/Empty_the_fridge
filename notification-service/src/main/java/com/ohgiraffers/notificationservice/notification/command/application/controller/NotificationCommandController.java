package com.ohgiraffers.notificationservice.notification.command.application.controller;

import com.ohgiraffers.notificationservice.common.dto.ApiResponse;
import com.ohgiraffers.notificationservice.notification.command.application.dto.request.NotificationCreateRequest;
import com.ohgiraffers.notificationservice.notification.command.application.service.NotificationCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "알림 관리", description = "알림 생성 및 관리 API")
@RestController
@RequiredArgsConstructor
public class NotificationCommandController {

    private final NotificationCommandService notificationCommandService;

    @Operation(
            summary = "알림 읽음 처리",
            description = "특정 알림을 읽음 상태로 표시합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "알림 읽음 처리 완료"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "알림을 찾을 수 없음")
    })
    @GetMapping("/notification/{notification-no}")
    public ResponseEntity<ApiResponse<Void>> checkNotification(
            @Parameter(description = "알림 번호", example = "1")
            @PathVariable("notification-no") Long notificationNo
    ) {
        this.notificationCommandService.checkNotification(notificationNo);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(
            summary = "알림 일괄 생성",
            description = "여러 알림을 한 번에 생성합니다 (다른 서비스의 Feign Client에서 사용)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "알림 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 알림 데이터")
    })
    @PostMapping("/notifications")
    public ResponseEntity<ApiResponse<Void>> createNotifications(
            @RequestBody List<NotificationCreateRequest> requests
    ) {
        this.notificationCommandService.createNotifications(requests);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
