package com.kwakmunsu.board.auth.controller.dto;

import com.kwakmunsu.board.auth.service.dto.NewMember;

public record MemberCreateRequest(
        String username,
        String password,
        String nickname
) {

    public NewMember toNewMember() {
        return new NewMember(username, password, nickname);
    }

}