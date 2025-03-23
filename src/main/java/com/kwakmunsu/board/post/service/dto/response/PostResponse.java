package com.kwakmunsu.board.post.service.dto.response;

import static com.kwakmunsu.board.util.TimeConverter.datetimeToString;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.post.entity.Post;
import java.util.List;
import lombok.Builder;

@Builder
public record PostResponse(
        long id,
        long writerId,
        String title,
        String content,
        String createAt,
        long likeCount,
        long viewCount,
        List<Comment> comments
) {

    public static PostResponse from(
            Post post,
            long likeCount,
            List<Comment> comments
    ) {
        return PostResponse.builder()
                .id(post.getId())
                .writerId(post.getWriterId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(datetimeToString(post.getCreatedAt()))
                .likeCount(likeCount)
                .viewCount(post.getViewCount())
                .comments(comments)
                .build();
    }

}