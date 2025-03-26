package com.kwakmunsu.board.auth.controller.dto;

import com.kwakmunsu.board.auth.service.dto.request.LoginServiceRequest;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "사용자 id를 입력해주세요")
        String username,

        @NotBlank(message = "비밀번호를 입력해주세요")
        String password
) {

    public LoginServiceRequest toServiceRequest() {
        return LoginServiceRequest.builder()
                .username(username)
                .password(password)
                .build();
    }

}