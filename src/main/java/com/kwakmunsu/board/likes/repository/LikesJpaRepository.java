package com.kwakmunsu.board.likes.repository;

import com.kwakmunsu.board.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesJpaRepository extends JpaRepository<Likes, Long> {

    boolean existsByPostIdAndMemberId(Long postId, Long memberId);
    void deleteByPostIdAndMemberId(Long postId, Long memberId);

}