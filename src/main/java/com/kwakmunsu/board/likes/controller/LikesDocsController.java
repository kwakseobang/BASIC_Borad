package com.kwakmunsu.board.likes.controller;


import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
import com.kwakmunsu.board.global.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "LikesController", description = "Likes API")
public interface LikesDocsController {

    @Operation(summary = "좋아요 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 등록 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 게시글입니다."),
            @ApiResponse(responseCode = "409", description = "이미 좋아요가 등록된 게시글입니다.")
    })
    ResponseEntity<ResponseData<?>> likePost(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    );

    @Operation(summary = "좋아요 취소")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 취소 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 게시글입니다."),
            @ApiResponse(responseCode = "409", description = "좋아요가 등록되지 않은 게시글입니다.")
    })
    ResponseEntity<ResponseData<?>> unLikePost(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
            );

}