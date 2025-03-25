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
    NOT_FOUND_MEMBER(NOT_FOUND.value(),"ERROR - 회원을 찾을 수 없습니다."),
    BAD_REQUEST_MEMBER(BAD_REQUEST.value(), "ERROR - 잘못된 회원 요청"),
    BAD_REQUEST_PASSWORD(BAD_REQUEST.value(), "ERROR - 잘못된 비밀번호 요청"),
    DUPLICATE_USERNAME(CONFLICT.value(), "ERROR - 회원가입 ID 중복"),
    DUPLICATE_NICKNAME(CONFLICT.value(), "ERROR - 회원가입 닉네임 중복"),

    // <=============== POST ===============>
    NOT_FOUND_POST(NOT_FOUND.value(), "ERROR - 해당 게시물을 찾을 수 없습니다."),
    BAD_REQUEST_POST(BAD_REQUEST.value(), "ERROR - 잘못된 게시물 요청"),

    // <=============== COMMENT ===============>
    NOT_FOUND_COMMENT(NOT_FOUND.value(), "ERROR - 해당 댓글을 찾을 수 없습니다."),
    BAD_REQUEST_COMMENT(BAD_REQUEST.value(), "ERROR - 잘못된 댓글 요청"),

    // <=============== LIKE ===============>
    FAILED_LIKE(CONFLICT.value(), "ERROR - 좋아요 등록 실패: "),
    FAILED_UNLIKE(CONFLICT.value(), "ERROR - 좋아요 취소 실패: "),

    // <=============== FAVORITES_POST ===============>
    FAILED_SAVE(CONFLICT.value(), "ERROR - 즐겨찾기 등록 실패"),
    FAILED_RELEASE(CONFLICT.value(), "ERROR - 즐겨찾기 취소 실패"),

    // <=============== PAGING ===============>
    FAILED_PAGING(BAD_REQUEST.value(), "ERROR - 정렬 기준이 잘못되었습니다."),

    // <=============== JWT ===============>
    TOKEN_EXPIRED(UNAUTHORIZED.value(), "ERROR - JWT 토큰 만료"),
    INVALID_TOKEN(UNAUTHORIZED.value(),"ERROR - 유효하지 않은 토큰입니다."),
    NOT_FOUND_TOKEN(NOT_FOUND.value(), "ERROR - 토큰을 찾을 수 없습니다."),
    BAD_REQUEST_TOKEN(BAD_REQUEST.value(), "ERROR - 잘못된 토큰 요청"),
    TOKEN_IS_BLACKLIST(UNAUTHORIZED.value(), "ERROR - 폐기된 토큰"),
    TOKEN_HASH_NOT_SUPPORTED(UNAUTHORIZED.value(),"ERROR - 지원하지 않는 형식의 토큰"),
    WRONG_AUTH_HEADER(UNAUTHORIZED.value(), "ERROR - [Bearer ]로 시작하는 토큰이 없습니다."),
    TOKEN_VALIDATION_TRY_FAILED(UNAUTHORIZED.value(),"ERROR - 토큰 인증 실패"),

    // <=============== ETC ===============>
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR - 서버 내부 에러"),
    UNAUTHORIZED_ERROR(UNAUTHORIZED.value(), "ERROR - 인증되지 않은 사용자입니다."),
    FORBIDDEN_ERROR(FORBIDDEN.value(), "ERROR - 접근 권한이 없습니다."),
    BAD_REQUEST_ARGUMENT(BAD_REQUEST.value(), "ERROR - 유효하지 않은 인자입니다."),
    ;

    private final int httpStatus;
    private final String message;

}