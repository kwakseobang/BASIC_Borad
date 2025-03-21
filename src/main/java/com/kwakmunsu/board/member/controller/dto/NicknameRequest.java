package com.kwakmunsu.board.member.controller.dto;

import com.kwakmunsu.board.member.service.dto.NicknameCreateCommand;
import com.kwakmunsu.board.util.JwtUtil;

public record NicknameRequest(String nickname) {

    public NicknameCreateCommand toNicknameCreateCommand() {
        Long memberId = JwtUtil.getCurrentMemberId();

        return NicknameCreateCommand.builder()
                .nickname(nickname)
                .memberId(memberId)
                .build();
    }

}