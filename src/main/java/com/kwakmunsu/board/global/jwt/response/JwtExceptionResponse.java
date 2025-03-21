package com.kwakmunsu.board.global.jwt.response;

import org.springframework.http.HttpStatus;

public record JwtExceptionResponse(
    HttpStatus httpStatus,
    int status,
    String message,
    String timestamp
) { }