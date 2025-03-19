package com.kwakmunsu.board.postLike.controller;


import com.kwakmunsu.board.postLike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;

}