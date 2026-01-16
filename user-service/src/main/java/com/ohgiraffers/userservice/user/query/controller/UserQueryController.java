package com.ohgiraffers.userservice.user.query.controller;


import com.ohgiraffers.userservice.common.dto.ApiResponse;
import com.ohgiraffers.userservice.user.query.dto.response.UserDetailResponse;
import com.ohgiraffers.userservice.user.query.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 조회", description = "회원 정보 조회 API")
@RestController
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryService userQueryService;

    @Operation(
            summary = "회원 ID로 조회",
            description = "로그인 ID로 회원 정보를 조회합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    @GetMapping("/users/{user_id}")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getUser(
            @Parameter(description = "회원 로그인 ID", example = "john_doe123")
            @PathVariable("user_id") String userId
    ) {
        UserDetailResponse response = this.userQueryService.getUser(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "회원 번호로 조회",
            description = "내부 회원 번호(PK)로 회원 정보를 조회합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    @GetMapping("/users/by-no/{user_no}")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getUserByUserNo(
            @Parameter(description = "회원 번호", example = "1")
            @PathVariable("user_no") Long userNo
    ) {
        UserDetailResponse response = this.userQueryService.getUserByUserNo(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "내 정보 조회",
            description = "로그인한 회원의 본인 정보를 조회합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "내 정보 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @GetMapping("/users/myinfo")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getMyInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserDetailResponse response = this.userQueryService.getUser(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
