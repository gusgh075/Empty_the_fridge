package com.ohgiraffers.userservice.user.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserPwdUpdateRequest {

    private final String resentPassword; // 기존 비번

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    // 정규표현식: 최소 8자, 영문(대소문자 상관없음)과 특수문자 각각 최소 1개 포함
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "비밀번호는 8자 이상이며, 영문자와 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private final String newPassword; // 나중 비번

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private final String checkNewPassword; // 한번더 체크

}
