package com.kwakmunsu.board.auth.service.dto.request;

import lombok.Builder;

@Builder
public record MemberCreateServiceRequest(
        String username,
        String password,
        String nickname
) {

}