package com.kwakmunsu.board.likes.controller;


import static com.kwakmunsu.board.util.JwtUtil.getCurrentMemberId;

import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.likes.service.LikesService;
import com.kwakmunsu.board.likes.service.dto.LikesCommand;
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
public class LikesController implements LikesApiController {

    private final LikesService likesService;

    @Operation(summary = "좋아요 등록")
    @PostMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> likePost(@PathVariable("postId") Long postId) {
        // TODO: getCurrentMemberId 로직 DTO 에서 처리 가능할 것 같습니다.
        Long memberId = getCurrentMemberId();
        LikesCommand likesCommand = new LikesCommand(postId, memberId);
        likesService.likePost(likesCommand);

        return ResponseData.success(SuccessCode.LIKE_SUCCESS);
    }

    @Operation(summary = "좋아요 취소")
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> unLikePost(@PathVariable("postId") Long postId) {
        Long memberId = getCurrentMemberId();
        LikesCommand likesCommand = new LikesCommand(postId, memberId);
        likesService.unlikePost(likesCommand);

        return ResponseData.success(SuccessCode.UNLIKE_SUCCESS);
    }

}