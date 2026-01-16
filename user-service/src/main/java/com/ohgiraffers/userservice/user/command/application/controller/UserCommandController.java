package com.ohgiraffers.userservice.user.command.application.controller;


import com.ohgiraffers.userservice.common.dto.ApiResponse;
import com.ohgiraffers.userservice.user.command.application.dto.request.UserCreateRequest;
import com.ohgiraffers.userservice.user.command.application.dto.request.UserPwdUpdateRequest;
import com.ohgiraffers.userservice.user.command.application.dto.request.UserUpdateRequest;
import com.ohgiraffers.userservice.user.command.application.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 관리", description = "회원 가입, 수정, 삭제 API")
@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;

    @Operation(
            summary = "회원 가입",
            description = "새로운 회원 계정을 생성합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "회원 가입 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 입력 데이터"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "이미 존재하는 회원")
    })
    @PostMapping("/users")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        this.userCommandService.registUser(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(null));
    }

    @Operation(
            summary = "회원 정보 수정",
            description = "회원의 이메일과 전화번호를 수정합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 정보 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    @PatchMapping("/users")
    public ResponseEntity<ApiResponse<Void>> updateUserInfo(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        this.userCommandService.updateUser(userDetails, userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }

    @Operation(
            summary = "비밀번호 변경",
            description = "현재 비밀번호 확인 후 새 비밀번호로 변경합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "비밀번호 변경 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 비밀번호 형식 또는 비밀번호 불일치"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "현재 비밀번호가 틀림")
    })
    @PatchMapping("/users/password")
    public ResponseEntity<ApiResponse<Void>> updatePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserPwdUpdateRequest userPwdUpdateRequest
    ) {
        this.userCommandService.updateUserPassword(userDetails, userPwdUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }

    @Operation(
            summary = "회원 탈퇴",
            description = "로그인한 회원의 계정을 영구 삭제합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 탈퇴 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @DeleteMapping("/users")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        this.userCommandService.deleteUser(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }

}
