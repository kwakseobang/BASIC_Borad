package com.kwakmunsu.board.comment.controller;

import static com.kwakmunsu.board.global.response.ResponseData.success;

import com.kwakmunsu.board.comment.controller.dto.CommentCreateRequest;
import com.kwakmunsu.board.comment.controller.dto.CommentUpdateRequest;
import com.kwakmunsu.board.comment.service.CommentService;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
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

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController implements CommentDocsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ResponseData<Long>> create(
            @CurrentLoginMember Long memberId,
            @Valid @RequestBody CommentCreateRequest request
    ) {
        Long commentId = commentService.create(memberId, request.toServiceRequest());
        return success(SuccessCode.CREATED_COMMENT, commentId);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ResponseData<CommentResponse>> read(
            @PathVariable("commentId") Long commentId
    ) {
        CommentResponse commentResponse = commentService.read(commentId);
        return success(SuccessCode.READ_COMMENT, commentResponse);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseData<?>> update(
            @PathVariable("commentId") Long commentId,
            @CurrentLoginMember Long memberId,
            @Valid @RequestBody CommentUpdateRequest request
    ) {
        commentService.update(commentId, memberId, request.toServiceRequest());
        return success(SuccessCode.UPDATE_COMMENT);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseData<?>> delete(
            @PathVariable("commentId") Long commentId,
            @CurrentLoginMember Long memberId
    ) {
        commentService.delete(commentId, memberId);
        return success(SuccessCode.DELETE_COMMENT);
    }

}