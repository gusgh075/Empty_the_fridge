package com.ohgiraffers.userservice.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public enum ErrorCode {

    // 유저 관련 오류
    USER_INACTIVE("1092","이미 탈퇴한 회원입니다", HttpStatus.BAD_REQUEST),

    // 상품 관련 오류
    USER_NOT_FOUND("10001", "ID가 일치하는 회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // 식자재 관련 오류
    QUANTITY_INPUT_ERROR("0101","잘못된 수량 입력입니다.", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_STOCK("0102","식재료 재고가 부족합니다.", HttpStatus.BAD_REQUEST),
    INGREDIENT_STOCK_NOT_FOUND("0103","식재료를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // 입력 값 검증 오류
    VALIDATION_ERROR("40001", "입력 값 검증 오류입니다.", HttpStatus.BAD_REQUEST),

    // 그 외 기타 오류
    INTERNAL_SERVER_ERROR("50000", "내부 서버 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
