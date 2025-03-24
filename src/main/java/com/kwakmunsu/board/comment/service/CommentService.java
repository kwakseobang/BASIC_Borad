package com.kwakmunsu.board.comment.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.infrastruture.CommentCommander;
import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.comment.service.dto.request.CommentCreateCommand;
import com.kwakmunsu.board.comment.service.dto.request.CommentUpdateCommand;
import com.kwakmunsu.board.comment.service.dto.response.CommentCreateResponse;
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

    public CommentCreateResponse create(CommentCreateCommand commentCreateCommand) {
        postReader.validatePostExist(commentCreateCommand.postId());

        long newCommentId = commentCommander.append(
                commentCreateCommand.content(),
                commentCreateCommand.postId(),
                commentCreateCommand.writerId()
        );
        return new CommentCreateResponse(newCommentId);
    }

    public CommentResponse read(Long commentId) {
        Comment comment = commentReader.read(commentId);

        return CommentResponse.from(comment);
    }

    public void update(CommentUpdateCommand commentUpdateCommand) {
        commentCommander.update(commentUpdateCommand.content(), commentUpdateCommand.commentId());
    }

    public void delete(Long commentId) {
        commentCommander.delete(commentId);
    }

}