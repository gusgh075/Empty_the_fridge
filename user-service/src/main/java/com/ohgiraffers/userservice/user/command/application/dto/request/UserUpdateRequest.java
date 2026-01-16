package com.ohgiraffers.userservice.user.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "회원 정보 수정 요청")
@Getter
@RequiredArgsConstructor
public class UserUpdateRequest {

    @Schema(description = "새 이메일 주소", example = "newemail@example.com")
    private final String email;

    @Schema(description = "새 전화번호", example = "010-9876-5432")
    private final String phoneNum;

}
