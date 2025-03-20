package com.kwakmunsu.board.global.exception.handler;

import com.kwakmunsu.board.global.exception.BoardException;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import java.nio.file.AccessDeniedException;
import javax.naming.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // TODO: 예외 핸들러 추가 예정
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<String>> handleException(Exception e) {
        return ResponseData.error(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseData<String>> handleUnauthorizedException(
        AuthenticationException e
    ) {
        return ResponseData.error(ErrorCode.UNAUTHORIZED_ERROR, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseData<String>> handleForbiddenException(AccessDeniedException e) {
        return ResponseData.error(ErrorCode.FORBIDDEN_ERROR, e.getMessage());
    }

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<ResponseData<String>> handleCustomException(BoardException ex) {
        return ResponseData.error(ex.getErrorCode(), ex.getMessage());
    }

}