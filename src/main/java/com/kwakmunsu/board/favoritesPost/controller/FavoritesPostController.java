package com.kwakmunsu.board.favoritesPost.controller;


import com.kwakmunsu.board.favoritesPost.service.FavoritesPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FavoritesPostController {

    private final FavoritesPostService favoritesPostService;

}