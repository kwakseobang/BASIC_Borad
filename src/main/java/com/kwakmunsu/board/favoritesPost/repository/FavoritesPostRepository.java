package com.kwakmunsu.board.favoritespost.repository;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;

public interface FavoritesPostRepository {

    void append(FavoritesPost favoritesPost);
    void cancel(Long postId, Long memberId);
    boolean isSave(Long postId, Long memberId);

}