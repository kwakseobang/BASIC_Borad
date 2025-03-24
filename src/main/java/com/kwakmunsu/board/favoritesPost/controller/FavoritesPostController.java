package com.kwakmunsu.board.favoritespost.controller;


import com.kwakmunsu.board.favoritespost.service.FavoritesPostService;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesCommand;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesPageResponse;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/favorites-posts")
@RequiredArgsConstructor
@RestController
public class FavoritesPostController implements FavoritesPostApiController {

    private final FavoritesPostService favoritesPostService;

    @PostMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> append(@PathVariable("postId") Long postId) {
        FavoritesCommand favoritesCommand = FavoritesCommand.from(postId);
        favoritesPostService.append(favoritesCommand);

        return ResponseData.success(SuccessCode.SAVE_POST_SUCCESS);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<FavoritesPageResponse>>> readAll() {
        List<FavoritesPageResponse> favoritesPageResponses = favoritesPostService.readAll();

        return ResponseData.success(SuccessCode.READ_FAVORITES_LIST, favoritesPageResponses);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> cancel(@PathVariable("postId") Long postId) {
        FavoritesCommand favoritesCommand = FavoritesCommand.from(postId);
        favoritesPostService.cancel(favoritesCommand);

        return ResponseData.success(SuccessCode.CANCEL_POST_SUCCESS);
    }

}