package com.kwakmunsu.board.likes.repository;


import com.kwakmunsu.board.likes.entity.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LikesRepositoryImpl implements LikesRepository {

    private final LikesJpaRepository likesJpaRepository;

    @Override
    public long read(Long postId) {
        return likesJpaRepository.countByPostId(postId);
    }

    @Override
    public void incrementLikes(Likes likes) {
        likesJpaRepository.save(likes);
    }

    @Override
    public void decrementLikes(Long postId, Long memberId) {
        likesJpaRepository.deleteByPostIdAndMemberId(postId, memberId);
    }

    @Override
    public boolean isLike(Long postId, Long memberId) {
        return likesJpaRepository.existsByPostIdAndMemberId(postId, memberId);
    }

}