package com.kwakmunsu.board.favoritespost.service.dto;

import static com.kwakmunsu.board.util.TimeConverter.datetimeToString;

import com.kwakmunsu.board.post.entity.Post;
import lombok.Builder;

@Builder
public record FavoritesPageResponse(
        long id,
        String title,
        String writer,
        String createAt,
        long viewCount,
        long likeCount,
        long favoritesCount
) {

    public static FavoritesPageResponse from(Post post, long likeCount, long favoritesCount) {
        return FavoritesPageResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getWriter().getNickname())
                .createAt(datetimeToString(post.getCreatedAt()))
                .viewCount(post.getViewCount())
                .likeCount(likeCount)
                .favoritesCount(favoritesCount)
                .build();
    }

}