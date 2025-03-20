package com.kwakmunsu.board.auth.controller.dto;

import com.kwakmunsu.board.auth.service.dto.LoginDto;

public record LoginRequest(String username, String password) {

    public LoginDto tologinDto() {
        return new LoginDto(username, password);
    }

}