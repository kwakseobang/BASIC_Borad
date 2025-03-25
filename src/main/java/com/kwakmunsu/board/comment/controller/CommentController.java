package com.kwakmunsu.board.comment.controller;


import com.kwakmunsu.board.comment.controller.dto.CommentCreateRequest;
import com.kwakmunsu.board.comment.controller.dto.CommentUpdateRequest;
import com.kwakmunsu.board.comment.service.CommentService;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import jakarta.validation.Valid;
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


@RequestMapping("/comments")
@RequiredArgsConstructor
@RestController
public class CommentController implements CommentApiController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ResponseData<Long>> create(
            @Valid @RequestBody CommentCreateRequest request
    ) {
        Long commentId = commentService.create(request.toCommentCreateCommand());

        return ResponseData.success(SuccessCode.CREATED_COMMENT, commentId);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ResponseData<CommentResponse>> read(
            @PathVariable("commentId") Long commentId
    ) {
        CommentResponse commentResponse = commentService.read(commentId);

        return ResponseData.success(SuccessCode.READ_COMMENT, commentResponse);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseData<?>> update(
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody CommentUpdateRequest request
    ) {
        commentService.update(request.toCommentUpdateCommand(commentId));

        return ResponseData.success(SuccessCode.UPDATE_COMMENT);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseData<?>> delete(@PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);

        return ResponseData.success(SuccessCode.DELETE_COMMENT);
    }

}