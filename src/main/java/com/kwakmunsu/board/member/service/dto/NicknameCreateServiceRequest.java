package com.kwakmunsu.board.member.service.dto;

import lombok.Builder;

@Builder
public record NicknameCreateServiceRequest(
        String nickname
) {

}