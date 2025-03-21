package com.kwakmunsu.board.favoritespost.service;

import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostUpdater;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostReader;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FavoritesPostService {

    private final FavoritesPostUpdater favoritesPostUpdater;
    private final FavoritesPostReader favoritesPostReader;

    public void append(FavoritesCommand favoritesCommand) {
        // 해당 유저가 해당 게시물을 저장하지 않았으면 유효성 검증 통과
        favoritesPostReader.validateNotSave(favoritesCommand.postId(),favoritesCommand.memberId());
        favoritesPostUpdater.append(favoritesCommand.postId(),favoritesCommand.memberId());
    }

    @Transactional
    public void cancel(FavoritesCommand favoritesCommand) {
        // 해당 유저가 해당 게시물을 저장했으면 유효성 검증 통과
        favoritesPostReader.validateSave(favoritesCommand.postId(),favoritesCommand.memberId());
        favoritesPostUpdater.cancel(favoritesCommand.postId(),favoritesCommand.memberId());
    }

}