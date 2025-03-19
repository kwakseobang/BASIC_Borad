package com.kwakmunsu.board.postLike.repository;

import com.kwakmunsu.board.postLike.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeJpaRepository extends JpaRepository<PostLike, Long> {

}