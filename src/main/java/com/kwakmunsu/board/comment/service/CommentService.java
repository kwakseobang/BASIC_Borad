package com.kwakmunsu.board.comment.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.infrastruture.CommentAppender;
import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.comment.infrastruture.CommentRemover;
import com.kwakmunsu.board.comment.infrastruture.CommentUpdater;
import com.kwakmunsu.board.comment.service.dto.request.CommentCreateCommand;
import com.kwakmunsu.board.comment.service.dto.request.CommentUpdateCommand;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentAppender commentAppender;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;
    private final CommentRemover commentRemover;

    public void create(CommentCreateCommand commentCreateCommand) {
        commentAppender.append(
                commentCreateCommand.content(),
                commentCreateCommand.postId(),
                commentCreateCommand.writerId()
        );
    }

    public CommentResponse read(Long commentId) {
        Comment comment = commentReader.read(commentId);

        return CommentResponse.from(comment);
    }

    public void update(CommentUpdateCommand commentUpdateCommand) {
        commentUpdater.update(commentUpdateCommand.content(), commentUpdateCommand.commentId());
    }

    public void delete(Long commentId) {
        commentRemover.delete(commentId);
    }

}