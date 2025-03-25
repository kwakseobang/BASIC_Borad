package com.kwakmunsu.board.comment.service.dto.request;

import lombok.Builder;

@Builder
public record CommentCreateServiceRequest(
        String content,
        Long postId
) {

}