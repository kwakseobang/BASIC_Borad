package com.kwakmunsu.board.global.response;

import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.util.TimeConverter;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;

@ToString
@Getter
@Builder
public class ResponseData<T> {

    private final int status;
    private final String message;
    private final StatusCode statusCode;
    private final String timestamp;
    private final T data;

    public static ResponseEntity<ResponseData<?>> success(SuccessCode successCode) {
        return ResponseEntity
            .status(successCode.getHttpStatus())
            .body(ResponseData.builder()
                .status(successCode.getHttpStatus())
                .message(successCode.getMessage())
                .statusCode(successCode)
                .timestamp(TimeConverter.datetimeToString(LocalDateTime.now()))
                .build()
            );
    }

    public static <T> ResponseEntity<ResponseData<T>> success(SuccessCode successCode, T data) {
        return ResponseEntity
            .status(successCode.getHttpStatus())
            .body(ResponseData.<T>builder()
                .status(successCode.getHttpStatus())
                .message(successCode.getMessage())
                .statusCode(successCode)
                .data(data)
                .timestamp(TimeConverter.datetimeToString(LocalDateTime.now()))
                .build()
            );
    }

    public static ResponseEntity<ResponseData<?>> error(ErrorCode errorCode) {
        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(ResponseData.builder()
                .status(errorCode.getHttpStatus())
                .message(errorCode.getMessage())
                .statusCode(errorCode)
                .timestamp(TimeConverter.datetimeToString(LocalDateTime.now()))
                .build()
            );
    }

    public static <T> ResponseEntity<ResponseData<T>> error(ErrorCode errorCode, T data) {
        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(ResponseData.<T>builder()
                .status(errorCode.getHttpStatus())
                .message(errorCode.getMessage())
                .statusCode(errorCode)
                .data(data)
                .timestamp(TimeConverter.datetimeToString(LocalDateTime.now()))
                .build()
            );
    }

}