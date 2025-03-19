package com.kwakmunsu.board.global.response.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.kwakmunsu.board.global.response.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements StatusCode {

    // <=============== MEMBER ===============>
    NOT_FOUND_MEMBER(NOT_FOUND.value(), ErrorMessage.NOT_FOUND_MEMBER.getMessage()),
    BAD_REQUEST_MEMBER(BAD_REQUEST.value(), ErrorMessage.BAD_REQUEST_MEMBER.getMessage()),
    BAD_REQUEST_PASSWORD(BAD_REQUEST.value(), ErrorMessage.BAD_REQUEST_PASSWORD.getMessage()),
    DUPLICATE_USERNAME(CONFLICT.value(), ErrorMessage.DUPLICATE_USERNAME.getMessage()),
    DUPLICATE_NICKNAME(CONFLICT.value(), ErrorMessage.DUPLICATE_NICKNAME.getMessage()),

    // <=============== POST ===============>
    NOT_FOUND_POST(NOT_FOUND.value(), ErrorMessage.NOT_FOUND_POST.getMessage()),
    BAD_REQUEST_POST(BAD_REQUEST.value(), ErrorMessage.BAD_REQUEST_POST.getMessage()),
    // TODO: 메세지 추가 예정

    // <=============== JWT ===============>
    TOKEN_EXPIRED(UNAUTHORIZED.value(), ErrorMessage.TOKEN_EXPIRED.getMessage()),
    INVALID_TOKEN(UNAUTHORIZED.value(), ErrorMessage.INVALID_TOKEN.getMessage()),
    NOT_FOUND_TOKEN(NOT_FOUND.value(), ErrorMessage.NOT_FOUND_TOKEN.getMessage()),
    BAD_REQUEST_TOKEN(BAD_REQUEST.value(), ErrorMessage.BAD_REQUEST_TOKEN.getMessage()),
    TOKEN_IS_BLACKLIST(UNAUTHORIZED.value(), ErrorMessage.TOKEN_IS_BLACKLIST.getMessage()),
    TOKEN_HASH_NOT_SUPPORTED(
        UNAUTHORIZED.value(),
        ErrorMessage.TOKEN_HASH_NOT_SUPPORTED.getMessage()
    ),
    WRONG_AUTH_HEADER(UNAUTHORIZED.value(), ErrorMessage.WRONG_AUTH_HEADER.getMessage()),
    TOKEN_VALIDATION_TRY_FAILED(
        UNAUTHORIZED.value(),
        ErrorMessage.TOKEN_VALIDATION_TRY_FAILED.getMessage()
    ),

    // <=============== ETC ===============>
    PREVENT_GET_ERROR(NO_CONTENT.value(), ErrorMessage.PREVENT_GET_ERROR.getMessage()),
    INTERNAL_SERVER_ERROR(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ErrorMessage.INTERNAL_SERVER_ERROR.getMessage()
    ),
    UNAUTHORIZED_ERROR(UNAUTHORIZED.value(), ErrorMessage.UNAUTHORIZED.getMessage()),
    FORBIDDEN_ERROR(FORBIDDEN.value(), ErrorMessage.FORBIDDEN.getMessage()),
    ;

    private final int httpStatus;
    private final String message;

}