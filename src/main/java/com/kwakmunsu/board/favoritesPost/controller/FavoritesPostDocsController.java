package com.kwakmunsu.board.favoritespost.controller;

import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.post.entity.dto.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "FavoritesPostController", description = "FavoritesPost API")
public interface FavoritesPostDocsController {

    @Operation(summary = "저장 목록에 게시글 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 저장 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 게시글로 저장 실패"),
            @ApiResponse(responseCode = "409", description = "이미 저장된 게시글입니다."),
    })
    ResponseEntity<ResponseData<?>> append(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    );

    @Operation(summary = "저장된 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    })
    ResponseEntity<ResponseData<List<PostResponse>>> readAll(
            @RequestParam(value = "lastPostId", required = false) Long lastPostId,
            @RequestParam(value = "lastViews", required = false) Long lastViews,
            @RequestParam(value = "lastTitle", required = false) String lastTitle,
            @RequestParam(value = "lastCreatedAt", required = false) LocalDateTime lastCreatedAt,
            @RequestParam(defaultValue = "createAt", value = "sortBy") String sortBy,
            @CurrentLoginMember Long memberId
    );

    @Operation(summary = "게시글 저장 취소")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "게시글 저장 취소 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 게시글로 저장 취소 실패"),
            @ApiResponse(responseCode = "409", description = "저장되지 않은 게시글입니다."),
    })
    ResponseEntity<ResponseData<?>> cancel(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    );

}