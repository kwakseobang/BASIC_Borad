package com.kwakmunsu.board.global.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    //  <=============== MEMBER ===============>
    NOT_FOUND_MEMBER("ERROR - 회원을 찾을 수 없습니다."),
    BAD_REQUEST_MEMBER("ERROR - 잘못된 회원 요청"),
    BAD_REQUEST_PASSWORD("ERROR - 잘못된 비밀번호 요청"),
    DUPLICATE_USERNAME("ERROR - 회원가입 ID 중복"),
    DUPLICATE_NICKNAME("ERROR - 회원가입 닉네임 중복"),

    // <=============== POST ===============>
    NOT_FOUND_POST("ERROR - 해당 게시물을 찾을 수 없습니다."),
    BAD_REQUEST_POST("ERROR - 잘못된 게시물 요청"),

    // <=============== COMMENT ===============>
    NOT_FOUND_COMMENT("ERROR - 해당 댓글을 찾을 수 없습니다."),
    BAD_REQUEST_COMMENT("ERROR - 잘못된 댓글 요청"),

    // <=============== LIKE ===============>
    FAILED_LIKE("ERROR - 좋아요 등록 실패: "),
    FAILED_UNLIKE("ERROR - 좋아요 취소 실패: "),

    // <=============== LIKE ===============>
    FAILED_SAVE("ERROR - 즐겨찾기 등록 실패"),
    FAILED_RELEASE("ERROR - 즐겨찾기 취소 실패"),

    //  <=============== JWT ===============>
    TOKEN_EXPIRED("ERROR - JWT 토큰 만료"),
    INVALID_TOKEN("ERROR - 유효하지 않은 토큰입니다."),
    NOT_FOUND_TOKEN("ERROR - 토큰을 찾을 수 없습니다."),
    BAD_REQUEST_TOKEN("ERROR - 잘못된 토큰 요청"),
    TOKEN_IS_BLACKLIST("ERROR - 폐기된 토큰"),
    TOKEN_HASH_NOT_SUPPORTED("ERROR - 지원하지 않는 형식의 토큰"),
    WRONG_AUTH_HEADER("ERROR - [Bearer ]로 시작하는 토큰이 없습니다."),
    TOKEN_VALIDATION_TRY_FAILED("ERROR - 토큰 인증 실패"),


    // <=============== ETC ===============>
    INTERNAL_SERVER_ERROR("ERROR - 서버 내부 에러"),
    UNAUTHORIZED("ERROR - Unauthorized"),
    FORBIDDEN("ERROR - Forbidden"),
    ;

    private final String message;

}