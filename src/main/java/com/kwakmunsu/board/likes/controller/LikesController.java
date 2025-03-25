package com.kwakmunsu.board.likes.controller;

import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.likes.service.LikesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/likes")
@RequiredArgsConstructor
@RestController
public class LikesController implements LikesDocsController {

    private final LikesService likesService;

    @Operation(summary = "좋아요 등록")
    @PostMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> likePost(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    ) {
        likesService.likePost(postId, memberId);

        return ResponseData.success(SuccessCode.LIKE_SUCCESS);
    }

    @Operation(summary = "좋아요 취소")
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> unLikePost(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    ) {
        likesService.unlikePost(postId, memberId);

        return ResponseData.success(SuccessCode.UNLIKE_SUCCESS);
    }

}