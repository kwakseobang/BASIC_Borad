package com.kwakmunsu.board.likes.service;

import com.kwakmunsu.board.global.exception.DuplicationException;
import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.likes.entity.Likes;
import com.kwakmunsu.board.likes.service.repository.LikesRepository;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesCommandService {

    private final LikesRepository likesRepository;
    private final PostRepository postRepository;

    public void likePost(Long postId, Long memberId) {
        validatePostExist(postId);

        // 유저가 게시물의 좋아요가 되어있지 않으면 유효성 검증 통과
        validateNotLiked(postId, memberId);
        incrementLikes(postId, memberId);
    }

    @Transactional
    public void unlikePost(Long postId, Long memberId) {
        validatePostExist(postId);

        // 유저가 게시물의 좋아요가 되어있어야 유효성 검증 통과
        validateLiked(postId, memberId);
        decrementLikes(postId, memberId);
    }

    private void validateNotLiked(Long postId, Long memberId) {
        if (likesRepository.isLike(postId, memberId)) {
            throw new DuplicationException(ErrorCode.FAILED_LIKE, "해당 게시물에 이미 좋아요를 누르셨습니다.");
        }
    }

    private void validatePostExist(Long postId) {
        if (postRepository.isExist(postId)) {
            return;
        }
        throw new NotFoundException((ErrorCode.NOT_FOUND_POST));
    }

    private void incrementLikes(Long postId, Long memberId) {
        Likes like = Likes.builder()
                .postId(postId)
                .memberId(memberId)
                .build();
        likesRepository.incrementLikes(like);
    }

    private void validateLiked(Long postId, Long memberId) {
        if (!likesRepository.isLike(postId, memberId)) {
            throw new DuplicationException(ErrorCode.FAILED_UNLIKE, "해당 게시물은 좋아요가 존재하지 않습니다");
        }
    }

    private void decrementLikes(Long postId, Long memberId) {
        if (likesRepository.isLike(postId, memberId)) {
            likesRepository.decrementLikes(postId, memberId);
        }
    }

}