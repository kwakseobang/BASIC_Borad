package com.kwakmunsu.board.post.service.dto.response;

import static com.kwakmunsu.board.util.TimeConverter.datetimeToString;

import com.kwakmunsu.board.comment.service.dto.response.CommentPreviewResponse;
import com.kwakmunsu.board.post.entity.Post;
import java.util.List;
import lombok.Builder;

@Builder
public record PostResponse(
        long id,
        String writer,
        String title,
        String content,
        String createAt,
        long viewCount,
        long likeCount,
        long favoritesCount,
        List<CommentPreviewResponse> commentPreviewResponses
) {

    public static PostResponse from(
            Post post,
            long likeCount,
            long favoritesCount,
            List<CommentPreviewResponse> commentPreviewResponses
    ) {
        return PostResponse.builder()
                .id(post.getId())
                .writer(post.getWriter().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(datetimeToString(post.getCreatedAt()))
                .viewCount(post.getViewCount())
                .likeCount(likeCount)
                .favoritesCount(favoritesCount)
                .commentPreviewResponses(commentPreviewResponses)
                .build();
    }

}