package com.kwakmunsu.board.auth.service.dto.request;

import lombok.Builder;

@Builder
public record LoginServiceRequest(
        String username,
        String password
) {

}