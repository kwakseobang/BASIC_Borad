package com.kwakmunsu.board.global.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    //  <=============== MEMBER ===============>
    NOT_FOUND_MEMBER("ERROR - 회원을 찾을 수 없습니다."),
    BAD_REQUEST_MEMBER("ERROR - 잘못된 회원 요청 에러"),
    BAD_REQUEST_PASSWORD("ERROR - 잘못된 비밀번호 요청 에러"),
    DUPLICATE_USERNAME("ERROR - 회원가입 ID 중복 에러"),
    DUPLICATE_NICKNAME("ERROR - 회원가입 닉네임 중복"),

    // <=============== POST ===============>
    NOT_FOUND_POST("ERROR - 해당 게시물을 찾을 수 없습니다."),
    BAD_REQUEST_POST("ERROR - 잘못된 게시물 요청 에러"),

    // <=============== COMMENT ===============>
    NOT_FOUND_COMMENT("ERROR - 해당 댓글을 찾을 수 없습니다."),
    BAD_REQUEST_COMMENT("ERROR - 잘못된 댓글 요청 에러"),

    //  <=============== JWT ===============>
    TOKEN_EXPIRED("ERROR - JWT 토큰 만료 에러"),
    INVALID_TOKEN("ERROR - 유효하지 않은 토큰입니다."),
    NOT_FOUND_TOKEN("ERROR - 토큰을 찾을 수 없습니다."),
    BAD_REQUEST_TOKEN("ERROR - 잘못된 토큰 요청 에러"),
    TOKEN_IS_BLACKLIST("ERROR - 폐기된 토큰"),
    TOKEN_HASH_NOT_SUPPORTED("ERROR - 지원하지 않는 형식의 토큰"),
    WRONG_AUTH_HEADER("ERROR - [Bearer ]로 시작하는 토큰이 없습니다."),
    TOKEN_VALIDATION_TRY_FAILED("ERROR - 토큰 인증 실패"),

    //  <=============== AWS ===============>
    AWS_S3_ERROR("ERROR - AWS S3 내부 에러"),
    AWS_S3_DELETE_ERROR("ERROR - S3 이미지 파일이 존재하지 않습니다."),
    AWS_S3_UPLOAD_ERROR("ERROR - S3 이미지 업로드 에러"),
    AWS_S3_TYPE_ERROR("ERROR - S3 허용되지 않는 파일 형식"),

    // <=============== ETC ===============>
    INTERNAL_SERVER_ERROR("ERROR - 서버 내부 에러"),
    UNAUTHORIZED("ERROR - Unauthorized 에러"), // 인증되지 않은 사용자
    FORBIDDEN("ERROR - Forbidden 에러"), // 권한 없을 때
    PREVENT_GET_ERROR("Status 204 - 리소스 및 리다이렉트 GET 호출 에러"),
    ;

    private final String message;

}