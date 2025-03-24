package com.kwakmunsu.board.post.service.dto.response;

import static com.kwakmunsu.board.util.TimeConverter.datetimeToString;

import com.kwakmunsu.board.post.entity.Post;
import lombok.Builder;

@Builder
public record PostPreviewResponse(
        long id,
        String title,
        String writer,
        String createAt,
        long viewCount,
        long likeCount,
        long favoritesCount
) {

    public static PostPreviewResponse from(
            Post post,
            long likeCount,
            long favoritesCount
    ) {
        return PostPreviewResponse.builder()
                .id(post.getId())
                .writer(post.getWriter().getNickname())
                .title(post.getTitle())
                .createAt(datetimeToString(post.getCreatedAt()))
                .viewCount(post.getViewCount())
                .likeCount(likeCount)
                .favoritesCount(favoritesCount)
                .build();
    }

}