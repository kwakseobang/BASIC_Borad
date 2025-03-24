package com.kwakmunsu.board.post.service.dto.response;

import com.kwakmunsu.board.comment.service.dto.response.CommentPageResponse;
import java.util.List;

public record PostDetailResponse(
        PostResponse postResponses,
        List<CommentPageResponse> commentPageResponses
) {

    public static PostDetailResponse of(
            PostResponse postResponses,
            List<CommentPageResponse> commentPageResponses
    ) {
        return new PostDetailResponse(postResponses, commentPageResponses);
    }

}