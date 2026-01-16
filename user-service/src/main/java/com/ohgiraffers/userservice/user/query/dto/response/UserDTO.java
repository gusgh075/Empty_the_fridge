package com.ohgiraffers.userservice.user.query.dto.response;

import com.ohgiraffers.userservice.user.command.domain.aggregate.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Schema(description = "회원 정보 DTO")
@Getter
@Builder
public class UserDTO {

    @Schema(description = "회원 번호", example = "1")
    private Long userNo;

    @Schema(description = "로그인 아이디", example = "john_doe123")
    private String id;

    @Schema(description = "암호화된 비밀번호 (응답에서 숨김)", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    @Schema(description = "닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "이메일", example = "john@example.com")
    private String email;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNum;

    @Schema(description = "생년월일", example = "1990-01-15")
    private String birthdate;

    @Schema(description = "가입일")
    private Date registeredAt;

    @Schema(description = "알림 활성화 상태", example = "Y")
    private String isNoticeActive;

    @Schema(description = "회원 권한", example = "USER")
    private UserRole role;

}
