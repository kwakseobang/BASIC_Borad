package com.kwakmunsu.board.comment.service.dto.response;

import static com.kwakmunsu.board.util.TimeConverter.datetimeToString;

import com.kwakmunsu.board.comment.entity.Comment;
import lombok.Builder;

@Builder
public record CommentResponse(
        Long commentId,
        String content,
        Long postId,
        Long writerId,
        String createAt
) {

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPostId())
                .writerId(comment.getWriterId())
                .createAt(datetimeToString(comment.getCreatedAt()))
                .build();
    }

}