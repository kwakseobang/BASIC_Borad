package com.kwakmunsu.board.favoritespost.controller;


import com.kwakmunsu.board.favoritespost.service.FavoritesPostService;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesCommand;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "favoritesPost")
@RequestMapping("/favorites-post")
@RequiredArgsConstructor
@RestController
public class FavoritesPostController {

    private final FavoritesPostService favoritesPostService;

    @PostMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> append(@PathVariable("postId") Long postId) {
        FavoritesCommand favoritesCommand = FavoritesCommand.from(postId);
        favoritesPostService.append(favoritesCommand);

        return ResponseData.success(SuccessCode.SAVE_POST_SUCCESS);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> cancel(@PathVariable("postId") Long postId) {
        FavoritesCommand favoritesCommand = FavoritesCommand.from(postId);
        favoritesPostService.cancel(favoritesCommand);

        return ResponseData.success(SuccessCode.CANCEL_POST_SUCCESS);
    }

}