package com.kwakmunsu.board.favoritespost.controller;

import static com.kwakmunsu.board.global.response.ResponseData.success;

import com.kwakmunsu.board.favoritespost.service.FavoritesCommandService;
import com.kwakmunsu.board.favoritespost.service.FavoritesQueryService;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesPreviewResponse;
import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
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
public class FavoritesPostController implements FavoritesPostDocsController {

    private final FavoritesCommandService favoritesCommandService;
    private final FavoritesQueryService favoritesQueryService;

    @PostMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> append(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    ) {
        favoritesCommandService.append(postId, memberId);
        return success(SuccessCode.SAVE_POST_SUCCESS);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<FavoritesPreviewResponse>>> readAll(
            @CurrentLoginMember Long memberId
    ) {
        return success(SuccessCode.READ_FAVORITES_LIST, favoritesQueryService.readAll());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> cancel(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    ) {
        favoritesCommandService.cancel(postId, memberId);
        return success(SuccessCode.CANCEL_POST_SUCCESS);
    }

}