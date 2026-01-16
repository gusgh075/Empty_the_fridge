package com.ohgiraffers.userservice.user.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "비밀번호 변경 요청")
@Getter
@RequiredArgsConstructor
public class UserPwdUpdateRequest {

    @Schema(description = "현재 비밀번호", example = "OldPassword@123", requiredMode = Schema.RequiredMode.REQUIRED)
    private final String resentPassword;

    @Schema(description = "새 비밀번호 (8자 이상, 영문자와 특수문자 포함 필수)", example = "NewPassword@456", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "비밀번호는 8자 이상이며, 영문자와 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private final String newPassword;

    @Schema(description = "새 비밀번호 확인 (새 비밀번호와 동일해야 함)", example = "NewPassword@456", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private final String checkNewPassword;

}
