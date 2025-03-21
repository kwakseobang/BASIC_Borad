package com.kwakmunsu.board.post.controller;

import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.post.controller.dto.PostCreateRequest;
import com.kwakmunsu.board.post.controller.dto.PostUpdateRequest;
import com.kwakmunsu.board.post.service.PostService;
import com.kwakmunsu.board.post.service.dto.request.PostDeleteCommand;
import com.kwakmunsu.board.post.service.dto.request.PostPageableCommand;
import com.kwakmunsu.board.post.service.dto.response.PostPageResponse;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import com.kwakmunsu.board.post.service.dto.response.PostViewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Post")
@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 생성 후 반환")
    @PostMapping
    public ResponseEntity<ResponseData<?>> create(@RequestBody PostCreateRequest request) {
        postService.create(request.toPostCreateCommand());

        return ResponseData.success(SuccessCode.CREATED_POST);
    }

    @Operation(summary = "게시물 상세 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseData<PostResponse>> read(@PathVariable("postId") Long postId) {
        PostResponse postResponse = postService.read(postId);

        return ResponseData.success(SuccessCode.READ_POST, postResponse);
    }

    @Operation(
            summary = "게시물 목록 조회 [내림차순]",
            description = "정렬: 생성일(createdAt=Default), 제목(title), 글 번호(id), 조회수(viewCount)"
                    + "page - 현재 요청한 페이지, pageSize - 페이지 당 표시할 게시글 개수"
                    + "page > 0"
    )
    @GetMapping
    public ResponseEntity<ResponseData<PostPageResponse>> readAll(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "createdAt")
            String sortBy
    ) {
        PostPageResponse postPageResponse = postService.readAll(
                new PostPageableCommand(page, pageSize, sortBy)
        );

        return ResponseData.success(SuccessCode.READ_POST, postPageResponse);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> update(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateRequest request
    ) {
        postService.update(request.toPostUpdateCommand(postId));

        return ResponseData.success(SuccessCode.UPDATE_POST);
    }

    @Operation(summary = "게시글 조회수 증가")
    @PostMapping("/{postId}/views")
    public ResponseEntity<ResponseData<?>> updateViews(@PathVariable("postId") Long postId) {
        postService.updateViews(postId);

        return ResponseData.success(SuccessCode.UPDATE_VIEWS);
    }

    @Operation(summary = "게시글 조회수 조회")
    @GetMapping("/{postId}/views")
    public ResponseEntity<ResponseData<PostViewsResponse>> readViews(
            @PathVariable("postId") Long postId
    ) {
        PostViewsResponse postViewsResponse = postService.readViews(postId);

        return ResponseData.success(SuccessCode.UPDATE_VIEWS, postViewsResponse);
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> delete(@PathVariable("postId") Long postId) {
        postService.delete(PostDeleteCommand.from(postId));

        return ResponseData.success(SuccessCode.DELETE_POST);
    }

}