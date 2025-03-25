package com.kwakmunsu.board.likes.controller;

import static com.kwakmunsu.board.global.response.ResponseData.success;

import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.likes.service.LikesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class LikesController implements LikesDocsController {

    private final LikesCommandService likesCommandService;

    @PostMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> likePost(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    ) {
        likesCommandService.likePost(postId, memberId);
        return success(SuccessCode.LIKE_SUCCESS);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> unLikePost(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    ) {
        likesCommandService.unlikePost(postId, memberId);
        return success(SuccessCode.UNLIKE_SUCCESS);
    }

}