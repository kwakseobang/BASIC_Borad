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
    CREATED_MEMBER(CREATED.value(), "SUCCESS - 회원 가입 성공"),
    READ_MEMBER(OK.value(), "SUCCESS - 회원 정보 조회 성공"),
    UPDATE_MEMBER(CREATED.value(), "SUCCESS - 회원 정보 수정 성공"),
    UPDATE_NICKNAME(CREATED.value(), "SUCCESS - 회원 닉네임 수정 성공"),
    DELETE_MEMBER(NO_CONTENT.value(), "SUCCESS - 회원 정보 삭제 성공"),

    // <=============== POST ===============>
    CREATED_POST(CREATED.value(), "SUCCESS - 게시물 등록 성공"),
    READ_POST(OK.value(), "SUCCESS - 게시물 조회 성공"),
    READ_POST_LIST(OK.value(), "SUCCESS - 게시물 목록 조회 성공"),
    UPDATE_POST(CREATED.value(), "SUCCESS - 게시물 수정 성공"),
    DELETE_POST(NO_CONTENT.value(), "SUCCESS - 게시물 삭제"),

    // <=============== VIEWS ===============>
    UPDATE_VIEWS(CREATED.value(), "SUCCESS - 조회수 증가 성공"),
    READ_VIEWS(OK.value(), "SUCCESS - 조회수 조회 성공"),

    // <=============== COMMENT ===============>
    CREATED_COMMENT(CREATED.value(), "SUCCESS - 댓글 등록 성공"),
    READ_COMMENT(OK.value(), "SUCCESS - 댓글 조회 성공"),
    UPDATE_COMMENT(CREATED.value(), "SUCCESS - 댓글 수정 성공"),
    DELETE_COMMENT(NO_CONTENT.value(), "SUCCESS - 댓글 삭제"),

    // <=============== LIKE ===============>
    LIKE_SUCCESS(CREATED.value(), "SUCCESS - 좋아요 등록 성공"),
    UNLIKE_SUCCESS(NO_CONTENT.value(), "SUCCESS - 좋아요 취소 성공"),

    // <=============== FAVORITES_POST ===============>
    SAVE_POST_SUCCESS(CREATED.value(), "SUCCESS - 게시물 저장 성공"),
    CANCEL_POST_SUCCESS(NO_CONTENT.value(), "SUCCESS - 게시물 저장 취소 성공"),
    READ_FAVORITES_LIST(OK.value(), "SUCCESS - 저장 게시물 목록 조회 성공"),

    // <=============== AUTH ===============>
    READ_IS_LOGIN(OK.value(), "SUCCESS - 현재 로그인 여부 조회 성공"),
    LOGIN_SUCCESS(OK.value(), "SUCCESS - 로그인 성공"),
    USERNAME_SUCCESS(OK.value(), "SUCCESS - 사용 가능한 아이디입니다."),
    NICKNAME_SUCCESS(OK.value(), "SUCCESS - 사용 가능한 닉네임입니다."),
    LOGOUT_SUCCESS(OK.value(), "SUCCESS - 로그아웃 성공 및 user Refresh Token 삭제"),
    UPDATE_PASSWORD(NO_CONTENT.value(), "SUCCESS - 비밀번호 수정 성공"),

    // <=============== JWT ===============>
    REISSUE_SUCCESS(OK.value(), "SUCCESS - JWT Access 토큰 재발급 성공"),
    ACCESS_TOKEN_SUCCESS(OK.value(), "SUCCESS - JWT Access 토큰 GET "),
    TOKEN_IS_VALID(OK.value(), "VALID - 유효한 토큰"),
    ;

    private final int httpStatus;
    private final String message;

}