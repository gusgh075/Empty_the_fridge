package com.ohgiraffers.hw22thteamproject.common.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/* 공통 응답 처리 클래스
 * - API 응답 형식을 통일해서 클라이언트가 일관된 방식으로 응답을 처리할 수 있도록 함.
 * */
@Getter
@Builder
public class ApiResponse<T> {

    private Boolean success;         // 요청 성공 여부
    private T data;                  // 실제 데이터 (성공 시 사용)
    private String errorCode;        // 에러 코드 (실패 시 사용)
    private String message;          // 에러 메시지
    private LocalDateTime timestamp; // 응답 생성 시간

    /* 성공 응답 생성 정적 메서드 */
    public static<T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /* 실패 응답 생성 정적 메서드 */
    public static<T> ApiResponse<T> failure(String errorCode, String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .errorCode(errorCode)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
