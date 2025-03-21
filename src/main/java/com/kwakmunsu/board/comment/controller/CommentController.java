package com.kwakmunsu.board.comment.controller;


import com.kwakmunsu.board.comment.controller.dto.CommentCreateRequest;
import com.kwakmunsu.board.comment.controller.dto.CommentUpdateRequest;
import com.kwakmunsu.board.comment.service.CommentService;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
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
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "comment")
@RequestMapping("/comments")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성")
    @PostMapping
    public ResponseEntity<ResponseData<?>> create(@RequestBody CommentCreateRequest request) {
        commentService.create(request.toCommentCreateCommand());

        return ResponseData.success(SuccessCode.CREATED_COMMENT);
    }

    @Operation(summary = "댓글 조회")
    @GetMapping("/{commentId}")
    public ResponseEntity<ResponseData<CommentResponse>> read(
            @PathVariable("commentId") Long commentId
    ) {
        CommentResponse commentResponse = commentService.read(commentId);

        return ResponseData.success(SuccessCode.READ_COMMENT, commentResponse);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseData<?>> update(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentUpdateRequest request
    ) {
        commentService.update(request.toCommentUpdateCommand(commentId));

        return ResponseData.success(SuccessCode.UPDATE_COMMENT);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseData<?>> delete(@PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);

        return ResponseData.success(SuccessCode.DELETE_COMMENT);
    }

}