package com.kwakmunsu.board.global.exception;

import com.kwakmunsu.board.global.response.error.ErrorCode;

public class ForbiddenException extends BoardException {

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ForbiddenException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}