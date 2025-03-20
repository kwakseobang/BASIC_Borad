package com.kwakmunsu.board.auth.controller.dto;

import com.kwakmunsu.board.auth.service.dto.NewMemberDto;

public record MemberCreateRequest(
        String username,
        String password,
        String nickname
) {

    public NewMemberDto toNewMember() {
        return new NewMemberDto(username, password, nickname);
    }

}