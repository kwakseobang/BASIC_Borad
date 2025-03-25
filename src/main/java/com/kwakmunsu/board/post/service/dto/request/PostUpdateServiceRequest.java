package com.kwakmunsu.board.post.service.dto.request;

import lombok.Builder;

@Builder
public record PostUpdateServiceRequest(
        String title,
        String content
) {

}