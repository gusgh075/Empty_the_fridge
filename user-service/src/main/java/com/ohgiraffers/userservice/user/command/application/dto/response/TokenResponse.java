package com.ohgiraffers.userservice.user.command.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "JWT 토큰 응답")
@Getter
@RequiredArgsConstructor
@Builder
public class TokenResponse {

    @Schema(description = "API 인증용 JWT 액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private final String accessToken;

    @Schema(description = "JWT 리프레시 토큰 (HttpOnly 쿠키로도 설정됨)", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private final String refreshToken;

}
