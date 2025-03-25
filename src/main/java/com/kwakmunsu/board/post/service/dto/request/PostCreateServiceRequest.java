package com.kwakmunsu.board.post.service.dto.request;

import lombok.Builder;

@Builder
public record PostCreateServiceRequest(
        String title,
        String content
) {

}