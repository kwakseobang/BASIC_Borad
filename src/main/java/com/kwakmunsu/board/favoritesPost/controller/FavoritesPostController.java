package com.kwakmunsu.board.favoritespost.controller;


import com.kwakmunsu.board.favoritespost.service.FavoritesPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FavoritesPostController {

    private final FavoritesPostService favoritesPostService;

}