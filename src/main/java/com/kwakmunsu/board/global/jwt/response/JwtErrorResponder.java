package com.kwakmunsu.board.global.jwt.response;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtErrorResponder {

    private final ObjectMapper objectMapper;

    public JwtErrorResponder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void sendErrorResponse(
            HttpServletResponse response,
            ErrorCode errorCode
    ) throws IOException {
        response.setCharacterEncoding("utf-8");
        JwtExceptionResponse responseJson = new JwtExceptionResponse(
            HttpStatus.UNAUTHORIZED,
            errorCode.getHttpStatus(),
            errorCode.getMessage(),
            LocalDateTime.now().toString()
        );

        String jsonToString = objectMapper.writeValueAsString(responseJson);
        response.getWriter().write(jsonToString);
    }

}