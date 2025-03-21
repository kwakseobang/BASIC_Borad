package com.kwakmunsu.board.comment.service.dto.response;

import com.kwakmunsu.board.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CommentResponse(
        Long commentId,
        String content,
        Long postId,
        Long writerId,
        LocalDateTime createAt
) {

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPostId())
                .writerId(comment.getWriterId())
                .createAt(comment.getCreatedAt())
                .build();
    }
}