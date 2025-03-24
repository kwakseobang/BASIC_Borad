package com.kwakmunsu.board.comment.service.dto.response;

import com.kwakmunsu.board.comment.entity.Comment;
import lombok.Builder;

@Builder
public record CommentPreviewResponse(
        Long id,
        Long writerId,
        String content
) {

    public static CommentPreviewResponse from(Comment comment) {
        return CommentPreviewResponse.builder()
                .id(comment.getId())
                .writerId(comment.getWriterId())
                .content(comment.getContent())
                .build();
    }

}