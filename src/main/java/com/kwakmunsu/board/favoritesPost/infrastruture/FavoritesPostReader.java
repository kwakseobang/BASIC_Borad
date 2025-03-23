package com.kwakmunsu.board.favoritespost.infrastruture;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import com.kwakmunsu.board.global.exception.DuplicationException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FavoritesPostReader {

    private final FavoritesPostRepository favoritesPostRepository;

    public List<FavoritesPost> readAll() {
        return  favoritesPostRepository.readAll();
    }
    public void validateNotSave(Long postId, Long memberId) {
        if (favoritesPostRepository.isSave(postId, memberId)) {
            throw new DuplicationException(ErrorCode.FAILED_SAVE, "이미 저장된 게시물 입니다.");
        }
    }

    public void validateSave(Long postId, Long memberId) {
        if (!favoritesPostRepository.isSave(postId, memberId)) {
            throw new DuplicationException(ErrorCode.FAILED_RELEASE, "저장하지 않은 게시물 입니다.");
        }
    }

}