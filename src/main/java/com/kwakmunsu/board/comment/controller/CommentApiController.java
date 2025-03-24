package com.kwakmunsu.board.comment.controller;


import com.kwakmunsu.board.comment.controller.dto.CommentCreateRequest;
import com.kwakmunsu.board.comment.controller.dto.CommentUpdateRequest;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import com.kwakmunsu.board.global.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "CommentController", description = "Comment API")
public interface CommentApiController {

    @Operation(summary = "댓글 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "댓글 생성 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 게시글로 댓글 생성 불가")
    })
    ResponseEntity<ResponseData<Long>> create(
            @RequestBody CommentCreateRequest request
    );

    @Operation(summary = "댓글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 댓글입니다.")
    })
    ResponseEntity<ResponseData<CommentResponse>> read(@PathVariable("commentId") Long commentId);

    @Operation(summary = "댓글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 댓글입니다.")
    })
    ResponseEntity<ResponseData<?>> update(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentUpdateRequest request
    );

    @Operation(summary = "댓글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 댓글입니다.")
    })
    ResponseEntity<ResponseData<?>> delete(@PathVariable("commentId") Long commentId);

}