package com.kwakmunsu.board.auth.service.dto;

public record NewMemberDto(
        String username,
        String password,
        String nickname
) {

}