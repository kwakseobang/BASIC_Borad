package com.kwakmunsu.board.postLike.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostLikeRepositoryImpl implements PostLikeRepository {

    private final PostLikeJpaRepository postLikeJpaRepository;

}