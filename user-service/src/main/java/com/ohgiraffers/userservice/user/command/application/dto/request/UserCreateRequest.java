package com.ohgiraffers.userservice.user.command.application.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.Date;

@Schema(description = "회원 가입 요청")
@Getter
@RequiredArgsConstructor
public class UserCreateRequest {

    @Schema(description = "로그인 아이디", example = "john_doe123", minLength = 8, maxLength = 15, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 8, max = 15, message = "아이디는 8자에서 15자 사이여야 합니다.")
    private final String userId;

    @Schema(description = "비밀번호 (8자 이상, 영문자와 특수문자 포함 필수)", example = "Password@123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "비밀번호는 8자 이상이며, 영문자와 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private final String password;

    @Schema(description = "닉네임", example = "홍길동")
    private final String nickname;

    @Schema(description = "이메일 주소", example = "john@example.com")
    private final String email;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private final String phoneNum;

    @Schema(description = "생년월일", example = "1990-01-15")
    private final Date birthdate;

}
