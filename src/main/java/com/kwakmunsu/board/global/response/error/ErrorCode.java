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

    // <=============== COMMENT ===============>
    NOT_FOUND_COMMENT(NOT_FOUND.value(), ErrorMessage.NOT_FOUND_COMMENT.getMessage()),
    BAD_REQUEST_COMMENT(BAD_REQUEST.value(), ErrorMessage.BAD_REQUEST_COMMENT.getMessage()),

    // <=============== LIKE ===============>
    FAILED_LIKE(CONFLICT.value(), ErrorMessage.FAILED_LIKE.getMessage()),
    FAILED_UNLIKE(CONFLICT.value(), ErrorMessage.FAILED_UNLIKE.getMessage()),

    // <=============== FAVORITES_POST ===============>
    FAILED_SAVE(CONFLICT.value(), ErrorMessage.FAILED_SAVE.getMessage()),
    FAILED_RELEASE(CONFLICT.value(), ErrorMessage.FAILED_RELEASE.getMessage()),

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