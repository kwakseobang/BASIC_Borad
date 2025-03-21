package com.kwakmunsu.board.post.service.dto.request;

import lombok.Builder;

@Builder
public record PostUpdateCommand(
        String title,
        String content,
        Long postId,
        Long memberId
) {

}