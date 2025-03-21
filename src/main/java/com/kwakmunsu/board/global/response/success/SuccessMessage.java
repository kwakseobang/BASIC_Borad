package com.kwakmunsu.board.global.response.success;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessMessage {

    // <=============== MEMBER ===============>
    CREATED_MEMBER("SUCCESS - 회원 가입 성공"),
    READ_MEMBER("SUCCESS - 회원 정보 조회 성공"),
    UPDATE_MEMBER("SUCCESS - 회원 정보 수정 성공"),
    UPDATE_NICKNAME("SUCCESS - 회원 닉네임 수정 성공"),
    DELETE_MEMBER("SUCCESS - 회원 정보 삭제 성공"),

    // <=============== POST ===============>
    CREATED_POST("SUCCESS - 게시물 등록 성공"),
    READ_POST("SUCCESS - 게시물 조회 성공"),
    READ_POST_LIST("SUCCESS - 게시물 목록 조회 성공"),
    UPDATE_POST("SUCCESS - 게시물 수정 성공"),
    DELETE_POST("SUCCESS - 게시물 삭제"),

    // <=============== VIEWS ===============>
    UPDATE_VIEWS("SUCCESS - 조회수 증가 성공"),
    READ_VIEWS("SUCCESS - 조회수 조회 성공"),

    // <=============== COMMENT ===============>
    CREATED_COMMENT("SUCCESS - 댓글 등록 성공"),
    READ_COMMENT("SUCCESS - 댓글 조회 성공"),
    UPDATE_COMMENT("SUCCESS - 댓글 수정 성공"),
    DELETE_COMMENT("SUCCESS - 댓글 삭제"),

    // <=============== LIKE ===============>
    LIKE_SUCCESS("SUCCESS - 좋아요 등록 성공"),
    UNLIKE_SUCCESS("SUCCESS - 좋아요 취소 성공"),

    // <=============== FAVORITES_POST ===============>
    SAVE_POST_SUCCESS("SUCCESS - 게시물 저장 성공"),
    CANCEL_POST_SUCCESS("SUCCESS - 게시물 저장 취소 성공"),

    // <=============== AUTH ===============>
    LOGIN_SUCCESS("SUCCESS - 로그인 성공"),
    LOGOUT_SUCCESS("SUCCESS - 로그아웃 성공 및 user Refresh Token 삭제"),
    UPDATE_PASSWORD("SUCCESS - 비밀번호 수정 성공"),
    USERNAME_SUCCESS("SUCCESS - 사용 가능한 아이디입니다."),
    NICKNAME_SUCCESS("SUCCESS - 사용 가능한 닉네임입니다."),
    READ_IS_LOGIN("SUCCESS - 현재 로그인 여부 조회 성공"),

    // <=============== JWT ===============>
    TOKEN_IS_VALID("VALID - 유효한 토큰"),
    REISSUE_SUCCESS("SUCCESS - JWT Access 토큰 재발급 성공"),
    ACCESS_TOKEN_SUCCESS("SUCCESS - JWT Access 토큰 GET "),
    ;

    private final String message;

}