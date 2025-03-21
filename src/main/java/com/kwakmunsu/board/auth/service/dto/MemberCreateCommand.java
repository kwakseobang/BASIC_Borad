package com.kwakmunsu.board.auth.service.dto;

import lombok.Builder;

@Builder
public record MemberCreateCommand(
        String username,
        String password,
        String nickname
) { }