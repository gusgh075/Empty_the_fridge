package com.ohgiraffers.ingredientstockservice.exception;



import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* 전역 예외 처리 클래스 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* 비즈니스 로직 상 발생한 예외 처리 */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Void> response
                = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());
        // return new ResponseEntity<>(response, errorCode.getHttpStatus());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }

    /* @Validated 유효성 검사 실패 예외 처리 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        StringBuilder errorMessage = new StringBuilder(errorCode.getMessage());
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(String.format("[%s : %s]", error.getField(), error.getDefaultMessage()));
        }
        ApiResponse<Void> response
                = ApiResponse.failure(errorCode.getCode(), errorMessage.toString());
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

}
