package com.kwakmunsu.board.comment.service.dto.request;

import lombok.Builder;

@Builder
public record CommentCreateCommand(
        String content,
        Long postId,
        Long writerId
) {

}