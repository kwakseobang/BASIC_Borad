package com.kwakmunsu.board.like.repository;

import com.kwakmunsu.board.like.entity.Like;
import com.kwakmunsu.board.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {

}