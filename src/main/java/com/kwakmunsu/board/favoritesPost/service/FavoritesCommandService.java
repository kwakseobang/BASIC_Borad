package com.kwakmunsu.board.favoritespost.service;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import com.kwakmunsu.board.global.exception.DuplicationException;
import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FavoritesCommandService {

    private final FavoritesPostRepository favoritesPostRepository;
    private final PostRepository postRepository;

    public void append(Long postId, Long memberId) {
        validatePostExist(postId);

        // 해당 유저가 해당 게시물을 저장하지 않았으면 유효성 검증 통과
        validateNotSave(postId, memberId);
        FavoritesPost favoritesPost = FavoritesPost.builder()
                .postId(postId)
                .memberId(memberId)
                .build();

        favoritesPostRepository.append(favoritesPost);
    }

    @Transactional
    public void cancel(Long postId, Long memberId) {
        validatePostExist(postId);

        // 해당 유저가 해당 게시물을 저장했으면 유효성 검증 통과
        validateSave(postId, memberId);
        if (favoritesPostRepository.isSave(postId, memberId)) {
            favoritesPostRepository.cancel(postId, memberId);
        }
    }

    private void validatePostExist(Long postId) {
        if (postRepository.isExist(postId)) {
            return;
        }
        throw new NotFoundException((ErrorCode.NOT_FOUND_POST));
    }

    private void validateNotSave(Long postId, Long memberId) {
        if (favoritesPostRepository.isSave(postId, memberId)) {
            throw new DuplicationException(ErrorCode.FAILED_SAVE, "이미 저장된 게시물 입니다.");
        }
    }

    private void validateSave(Long postId, Long memberId) {
        if (!favoritesPostRepository.isSave(postId, memberId)) {
            throw new DuplicationException(ErrorCode.FAILED_RELEASE, "저장하지 않은 게시물 입니다.");
        }
    }

}