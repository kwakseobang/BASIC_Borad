package com.kwakmunsu.board.post.controller;

import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.post.controller.dto.PostCreateRequest;
import com.kwakmunsu.board.post.controller.dto.PostUpdateRequest;
import com.kwakmunsu.board.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<ResponseData<?>> read(@PathVariable("postId") Long postId) {
        // 게시물을 조회힌다.. 게시물 아이디를 넘긴다.
        // 조회할 때는 게시물, 댓글과 좋아요 수, 조회 수를 넘겨준다.
//        postService.read(postId);
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



}