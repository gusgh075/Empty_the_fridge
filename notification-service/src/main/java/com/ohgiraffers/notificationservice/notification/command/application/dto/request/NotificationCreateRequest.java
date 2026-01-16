package com.ohgiraffers.notificationservice.notification.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "알림 생성 요청")
@Getter
@Builder
public class NotificationCreateRequest {

    @Schema(description = "알림을 받을 회원 번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userNo;

    @Schema(description = "알림 유형 번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer notificationTypeNo;

    @Schema(description = "알림 내용", example = "'우유'의 유통기한이 내일까지입니다!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String notificationContent;
}
