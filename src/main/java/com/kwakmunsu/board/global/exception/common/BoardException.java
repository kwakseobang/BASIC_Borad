package com.kwakmunsu.board.global.exception.common;

import com.kwakmunsu.board.global.response.error.ErrorCode;
import lombok.Getter;

@Getter
public abstract class BoardException extends RuntimeException {

    private final ErrorCode errorCode;
    private String message;

    protected BoardException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    protected BoardException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}