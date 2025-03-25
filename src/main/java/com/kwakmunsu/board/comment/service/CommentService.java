package com.kwakmunsu.board.comment.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.infrastruture.CommentCommander;
import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.comment.service.dto.request.CommentCreateServiceRequest;
import com.kwakmunsu.board.comment.service.dto.request.CommentUpdateServiceRequest;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentCommander commentCommander;
    private final CommentReader commentReader;
    private final PostReader postReader;

    public Long create(
            Long memberId,
            CommentCreateServiceRequest request
    ) {
        postReader.validatePostExist(request.postId());
        return commentCommander.append(
                memberId,
                request.postId(),
                request.content()
        );
    }

    public CommentResponse read(
            Long commentId
    ) {
        Comment comment = commentReader.read(commentId);
        return CommentResponse.from(comment);
    }

    public void update(
            Long commentId,
            Long memberId,
            CommentUpdateServiceRequest request
    ) {
        commentReader.validateAccess(commentId, memberId);
        commentCommander.update(commentId, request.content());
    }

    public void delete(
            Long commentId,
            Long memberId
    ) {
        commentReader.validateAccess(commentId, memberId);
        commentCommander.delete(commentId);
    }

}