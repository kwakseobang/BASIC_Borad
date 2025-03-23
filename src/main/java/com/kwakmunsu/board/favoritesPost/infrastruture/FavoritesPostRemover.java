package com.kwakmunsu.board.favoritespost.infrastruture;


import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FavoritesPostRemover {

    private final FavoritesPostRepository favoritesPostRepository;

    public void delete(Long postId, Long memberId) {

    }

}