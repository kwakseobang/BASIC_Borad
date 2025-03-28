package com.kwakmunsu.board.global.exception;

import com.kwakmunsu.board.global.exception.common.BoardException;
import com.kwakmunsu.board.global.response.error.ErrorCode;

public class BadRequestException extends BoardException {

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}