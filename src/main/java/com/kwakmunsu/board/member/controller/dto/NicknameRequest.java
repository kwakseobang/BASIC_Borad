package com.kwakmunsu.board.member.controller.dto;

import com.kwakmunsu.board.member.service.dto.NewNicknameDto;

public record NicknameRequest(String nickname) {

    public NewNicknameDto toNewNicknameDto() {
        return new NewNicknameDto(nickname);
    }
}