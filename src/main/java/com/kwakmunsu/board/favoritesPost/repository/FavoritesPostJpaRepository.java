package com.kwakmunsu.board.favoritespost.repository;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesPostJpaRepository extends JpaRepository<FavoritesPost, Long> {

    boolean existsByPostIdAndMemberId(Long postId, Long memberId);
    void deleteByPostIdAndMemberId(Long postId, Long memberId);

}