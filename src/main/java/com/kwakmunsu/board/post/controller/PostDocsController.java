package com.kwakmunsu.board.post.controller;

import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.post.controller.dto.PostCreateRequest;
import com.kwakmunsu.board.post.controller.dto.PostUpdateRequest;
import com.kwakmunsu.board.post.service.dto.response.PostPreviewResponse;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "PostController", description = "Post API")
public interface PostDocsController {

    @Operation(summary = "게시글 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 생성 성공")
    })
    ResponseEntity<ResponseData<Long>> create(
            @CurrentLoginMember Long memberId,
            @RequestBody PostCreateRequest request
    );

    @Operation(summary = "게시물 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물입니다."),
    })
    ResponseEntity<ResponseData<PostResponse>> read(@PathVariable("postId") Long postId);

    @Operation(
            summary = "게시물 목록 조회 [내림차순]",
            description = "정렬: 생성일(createdAt : Default), 제목(title), 글 번호(id), 조회수(viewCount)"
                    + "page - 현재 요청한 페이지, pageSize - 페이지 당 표시할 게시글 개수"
                    + "page > 0"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물입니다."),
    })
    ResponseEntity<ResponseData<List<PostPreviewResponse>>> readAll(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "createdAt")
            String sortBy,
            @RequestParam("isDesc") boolean isDesc
    );

    @Operation(summary = "게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물입니다."),
    })
    ResponseEntity<ResponseData<?>> update(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId,
            @RequestBody PostUpdateRequest request
    );

    @Operation(summary = "게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물입니다."),
    })
    ResponseEntity<ResponseData<?>> delete(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    );

    @Operation(summary = "게시글 조회수 증가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 조회수 증가 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물입니다."),
    })
    ResponseEntity<ResponseData<?>> updateViews(@PathVariable("postId") Long postId);

    @Operation(summary = "게시글 조회수 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 조회수 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물입니다."),
    })
    ResponseEntity<ResponseData<Long>> readViews(@PathVariable("postId") Long postId);

}