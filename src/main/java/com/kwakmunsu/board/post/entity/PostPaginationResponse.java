package com.kwakmunsu.board.post.entity;

import lombok.Builder;

@Builder
public record PostPaginationResponse(
        Long postId,
        String title,
        String writer,
        String createAt,
        Long viewCount,
        Long likeCount,
        Long favoritesCount
) {

}