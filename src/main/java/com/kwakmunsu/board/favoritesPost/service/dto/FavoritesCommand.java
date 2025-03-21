package com.kwakmunsu.board.favoritespost.service.dto;

import com.kwakmunsu.board.util.JwtUtil;
import lombok.Builder;

@Builder
public record FavoritesCommand(Long postId, Long memberId) {

    public static FavoritesCommand from(Long postId) {
        return FavoritesCommand.builder()
                .postId(postId)
                .memberId(JwtUtil.getCurrentMemberId())
                .build();
    }

}