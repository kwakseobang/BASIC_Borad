package com.kwakmunsu.board.global.response.success;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.kwakmunsu.board.global.response.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessCode implements StatusCode {

    // <=============== MEMBER ===============>
    CREATED_MEMBER(CREATED.value(), SuccessMessage.CREATED_MEMBER.getMessage()),
    READ_MEMBER(OK.value(), SuccessMessage.READ_MEMBER.getMessage()),
    UPDATE_MEMBER(CREATED.value(), SuccessMessage.UPDATE_MEMBER.getMessage()),
    UPDATE_NICKNAME(CREATED.value(), SuccessMessage.UPDATE_NICKNAME.getMessage()),
    DELETE_MEMBER(NO_CONTENT.value(), SuccessMessage.DELETE_MEMBER.getMessage()),

    // <=============== POST ===============>
    CREATED_POST(CREATED.value(), SuccessMessage.CREATED_POST.getMessage()),
    READ_POST(OK.value(), SuccessMessage.READ_POST.getMessage()),
    READ_POST_LIST(OK.value(), SuccessMessage.READ_POST_LIST.getMessage()),
    UPDATE_POST(CREATED.value(), SuccessMessage.UPDATE_POST.getMessage()),
    DELETE_POST(NO_CONTENT.value(), SuccessMessage.DELETE_POST.getMessage()),

    // <=============== COMMENT ===============>
    CREATED_COMMENT(CREATED.value(), SuccessMessage.CREATED_COMMENT.getMessage()),
    READ_COMMENT(OK.value(), SuccessMessage.READ_COMMENT.getMessage()),
    UPDATE_COMMENT(CREATED.value(), SuccessMessage.UPDATE_COMMENT.getMessage()),
    DELETE_COMMENT(NO_CONTENT.value(), SuccessMessage.DELETE_COMMENT.getMessage()),

    // <=============== LIKE ===============>
    LIKE_SUCCESS(CREATED.value(), SuccessMessage.LIKE_SUCCESS.getMessage()),
    UNLIKE_SUCCESS(NO_CONTENT.value(), SuccessMessage.UNLIKE_SUCCESS.getMessage()),

    // <=============== FAVORITES_POST ===============>
    SAVE_POST_SUCCESS(CREATED.value(), SuccessMessage.SAVE_POST_SUCCESS.getMessage()),
    CANCEL_POST_SUCCESS(NO_CONTENT.value(), SuccessMessage.CANCEL_POST_SUCCESS.getMessage()),

    // <=============== AUTH ===============>
    READ_IS_LOGIN(OK.value(), SuccessMessage.READ_IS_LOGIN.getMessage()),
    LOGIN_SUCCESS(OK.value(), SuccessMessage.LOGIN_SUCCESS.getMessage()),
    USERNAME_SUCCESS(OK.value(), SuccessMessage.USERNAME_SUCCESS.getMessage()),
    NICKNAME_SUCCESS(OK.value(), SuccessMessage.NICKNAME_SUCCESS.getMessage()),
    LOGOUT_SUCCESS(OK.value(), SuccessMessage.LOGOUT_SUCCESS.getMessage()),
    UPDATE_PASSWORD(NO_CONTENT.value(), SuccessMessage.UPDATE_PASSWORD.getMessage()),

    // <=============== JWT ===============>
    REISSUE_SUCCESS(OK.value(), SuccessMessage.REISSUE_SUCCESS.getMessage()),
    ACCESS_TOKEN_SUCCESS(OK.value(), SuccessMessage.ACCESS_TOKEN_SUCCESS.getMessage()),
    TOKEN_IS_VALID(OK.value(), SuccessMessage.TOKEN_IS_VALID.getMessage()),
    ;

    private final int httpStatus;
    private final String message;

}