package com.kwakmunsu.board.favoritespost.controller;


import com.kwakmunsu.board.favoritespost.service.dto.FavoritesResponse;
import com.kwakmunsu.board.global.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "FavoritesPostController", description = "FavoritesPost API")
public interface FavoritesPostApiController {

    @Operation(summary = "저장 목록에 게시글 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 저장 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 게시글로 저장 실패"),
            @ApiResponse(responseCode = "409", description = "이미 저장된 게시글입니다."),
    })
    ResponseEntity<ResponseData<?>> append(@PathVariable("postId") Long postId);

    @Operation(summary = "저장된 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    })
    ResponseEntity<ResponseData<FavoritesResponse>> readAll();

    @Operation(summary = "게시글 저장 취소")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "게시글 저장 취소 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 게시글로 저장 취소 실패"),
            @ApiResponse(responseCode = "409", description = "저장되지 않은 게시글입니다."),
    })
    ResponseEntity<ResponseData<?>> cancel(@PathVariable("postId") Long postId);

}