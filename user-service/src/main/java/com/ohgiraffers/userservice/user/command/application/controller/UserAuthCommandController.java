package com.ohgiraffers.userservice.user.command.application.controller;


import com.ohgiraffers.userservice.common.dto.ApiResponse;
import com.ohgiraffers.userservice.user.command.application.dto.request.UserLoginRequest;
import com.ohgiraffers.userservice.user.command.application.dto.response.TokenResponse;
import com.ohgiraffers.userservice.user.command.application.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Tag(name = "인증", description = "로그인/로그아웃 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserAuthCommandController {

    private final UserCommandService userCommandService;

    @Operation(
            summary = "로그인",
            description = "사용자 인증 후 JWT 액세스 토큰을 반환합니다. 리프레시 토큰은 HttpOnly 쿠키로 설정됩니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공, 액세스 토큰 반환"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody UserLoginRequest loginRequest) {
        TokenResponse tokenResponse = this.userCommandService.login(loginRequest);
        return buildTokenResponse(tokenResponse);
    }

    @Operation(
            summary = "로그아웃",
            description = "리프레시 토큰을 무효화하고 쿠키를 삭제하여 로그아웃합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그아웃 성공")
    })
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @Parameter(description = "쿠키의 리프레시 토큰", hidden = true)
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {
        // refresh token이 존재할 경우 -> login 상태
        if (refreshToken != null) {
            this.userCommandService.logout(refreshToken); // DB refresh token 삭제
        }

        ResponseCookie deleteCookie = createDeleteRefreshTokenCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body(ApiResponse.success(null));
    }

    /* refreshToken 쿠키를 삭제하는 delete cookie 생성 */
    private ResponseCookie createDeleteRefreshTokenCookie() {
        return ResponseCookie.from("refreshToken")
                .httpOnly(true)                     // HttpOnly 속성 설정 (JavaScript 에서 접근 불가)
                // .secure(true)                    // HTTPS 환경일 때만 전송 (운영 환경에서 활성화 권장)
                .path("/")                          // 쿠키 범위 : 전체 경로
                .maxAge(0)            // 쿠키 만료 기간 : 0초
                .sameSite("Strict")                 // CSRF 공격 방어를 위한 SameSite 설정
                .build();
    }

    /* accessToken 과 refreshToken을 body와 쿠키에 담아 반환 */
    private ResponseEntity<ApiResponse<TokenResponse>> buildTokenResponse(TokenResponse tokenResponse) {
        ResponseCookie cookie = createRefreshTokenCookie(tokenResponse.getRefreshToken());  // refreshToken 쿠키 생성
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(ApiResponse.success(tokenResponse));
    }

    /* refreshToken 쿠키 생성 */
    private ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)                     // HttpOnly 속성 설정 (JavaScript 에서 접근 불가)
                // .secure(true)                    // HTTPS 환경일 때만 전송 (운영 환경에서 활성화 권장)
                .path("/")                          // 쿠키 범위 : 전체 경로
                .maxAge(Duration.ofDays(7))         // 쿠키 만료 기간 : 7일
                .sameSite("Strict")                 // CSRF 공격 방어를 위한 SameSite 설정
                .build();
    }

}
