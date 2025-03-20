package com.kwakmunsu.board.favoritespost.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FavoritesPostRepositoryImpl implements FavoritesPostRepository {

    private final FavoritesPostJpaRepository favoritesPostJpaRepository;

}