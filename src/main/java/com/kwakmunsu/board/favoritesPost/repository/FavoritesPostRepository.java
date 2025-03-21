package com.kwakmunsu.board.favoritespost.repository;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import java.util.List;

public interface FavoritesPostRepository {

    List<FavoritesPost> readAll();
    void append(FavoritesPost favoritesPost);
    void cancel(Long postId, Long memberId);
    boolean isSave(Long postId, Long memberId);

}