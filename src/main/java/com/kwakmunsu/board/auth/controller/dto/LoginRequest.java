package com.kwakmunsu.board.auth.controller.dto;

import com.kwakmunsu.board.auth.service.dto.LoginCommand;

public record LoginRequest(String username, String password) {

    public LoginCommand tologinCommand() {
        return LoginCommand.builder()
                .username(username)
                .password(password)
                .build();
    }

}