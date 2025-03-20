package com.kwakmunsu.board.auth.service.dto;

public record NewMember(
        String username,
        String password,
        String nickname
) {

}