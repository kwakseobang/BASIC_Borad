package com.kwakmunsu.board.global.exception;

import com.kwakmunsu.board.global.response.error.ErrorCode;

public class UnAuthenticationException extends BoardException {

    public UnAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UnAuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}