package com.kwakmunsu.board.comment.service.dto.response;

import lombok.Builder;

@Builder
public record CommentPreviewResponse(
        Long commentId,
        Long writerId,
        String content
) {

}