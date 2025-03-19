package com.kwakmunsu.board.global.exception;

import com.kwakmunsu.board.global.response.error.ErrorCode;

public class NotFoundException extends BoardException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}