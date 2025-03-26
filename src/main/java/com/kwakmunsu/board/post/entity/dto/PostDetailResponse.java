package com.kwakmunsu.board.post.entity.dto;

import com.kwakmunsu.board.comment.service.dto.response.CommentPreviewResponse;
import java.util.List;

public record PostDetailResponse(
        PostResponse postResponse,
        List<CommentPreviewResponse> commentPreviewResponses
) {

}