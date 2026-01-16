package com.ohgiraffers.userservice.user.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "로그인 요청")
@Getter
@RequiredArgsConstructor
public class UserLoginRequest {

    @Schema(description = "로그인 아이디", example = "john_doe123", requiredMode = Schema.RequiredMode.REQUIRED)
    private final String userId;

    @Schema(description = "비밀번호", example = "Password@123", requiredMode = Schema.RequiredMode.REQUIRED)
    private final String password;

}
