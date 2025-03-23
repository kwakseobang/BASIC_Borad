package com.kwakmunsu.board.likes.infrastruture;


import com.kwakmunsu.board.global.exception.DuplicationException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.likes.service.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LikesReader {

    private final LikesRepository likesRepository;

    public long readLikes(Long postId) {
        return likesRepository.read(postId);
    }

    public void validateNotLiked(Long postId, Long memberId) {
        if (likesRepository.isLike(postId, memberId)) {
            throw new DuplicationException(ErrorCode.FAILED_LIKE, "해당 게시물에 이미 좋아요를 누르셨습니다.");
        }
    }

    public void validateLiked(Long postId, Long memberId) {
        if (!likesRepository.isLike(postId, memberId)) {
            throw new DuplicationException(ErrorCode.FAILED_UNLIKE, "해당 게시물은 좋아요가 존재하지 않습니다");
        }
    }

}