package com.kwakmunsu.board.post.service.dto.response;

import com.kwakmunsu.board.comment.service.dto.response.CommentPageResponse;
import java.util.List;

public record PostDetailResponse(
        PostResponse postResponse,
        List<CommentPageResponse> commentPageResponses
) {}