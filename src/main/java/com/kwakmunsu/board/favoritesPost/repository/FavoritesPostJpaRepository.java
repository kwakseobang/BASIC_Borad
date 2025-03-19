package com.kwakmunsu.board.favoritesPost.repository;

import com.kwakmunsu.board.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesPostJpaRepository extends JpaRepository<Comment, Long> {

}