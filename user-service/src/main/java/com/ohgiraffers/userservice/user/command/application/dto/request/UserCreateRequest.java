package com.ohgiraffers.userservice.user.command.application.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.Date;

@Getter
@RequiredArgsConstructor
public class UserCreateRequest {

    @NotNull(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 8, max = 15, message = "아이디는 8자에서 15자 사이여야 합니다.")
    private final String userId;

    @NotNull(message = "비밀번호는 필수 입력 값입니다.")
    // 정규표현식: 최소 8자, 영문(대소문자 상관없음)과 특수문자 각각 최소 1개 포함
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "비밀번호는 8자 이상이며, 영문자와 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private final String password;

    private final String nickname;

    private final String email;

    private final String phoneNum;

    private final Date birthdate;

}
