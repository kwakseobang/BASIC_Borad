package com.kwakmunsu.board.global.exception;

import com.kwakmunsu.board.global.response.error.ErrorCode;

public class DuplicationException extends BoardException {

    public DuplicationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}