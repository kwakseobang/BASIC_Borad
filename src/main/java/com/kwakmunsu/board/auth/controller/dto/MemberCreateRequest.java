package com.kwakmunsu.board.auth.controller.dto;

import com.kwakmunsu.board.auth.service.dto.request.MemberCreateCommand;

public record MemberCreateRequest(
        String username,
        String password,
        String nickname
) {

    public MemberCreateCommand toMemberCreateCommand() {
        return MemberCreateCommand.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }

}