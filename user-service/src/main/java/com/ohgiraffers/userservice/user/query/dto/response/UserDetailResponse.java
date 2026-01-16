package com.ohgiraffers.userservice.user.query.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "회원 상세 정보 응답")
@Getter
@Builder
public class UserDetailResponse {

    @Schema(description = "회원 정보")
    private final UserDTO user;

}
