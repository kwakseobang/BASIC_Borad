package com.kwakmunsu.board.comment.service.dto.response;

import com.kwakmunsu.board.comment.entity.Comment;
import lombok.Builder;

@Builder
public record CommentPageResponse(
        Long id,
        Long writerId,
        String content
) {

    public static CommentPageResponse from(Comment comment) {
        return CommentPageResponse.builder()
                .id(comment.getId())
                .writerId(comment.getWriterId())
                .content(comment.getContent())
                .build();
    }

}