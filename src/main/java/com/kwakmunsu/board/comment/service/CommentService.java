package com.kwakmunsu.board.comment.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.infrastruture.CommentAppender;
import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.comment.infrastruture.CommentRemover;
import com.kwakmunsu.board.comment.infrastruture.CommentUpdater;
import com.kwakmunsu.board.comment.service.dto.request.CommentCreateCommand;
import com.kwakmunsu.board.comment.service.dto.request.CommentUpdateCommand;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentAppender commentAppender;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;
    private final CommentRemover commentRemover;
    private final PostReader postReader;

    public void create(CommentCreateCommand commentCreateCommand) {
        // FIXME: 게시글 존재하는 지 유효성 검사가 빠져있습니다. 구현해 주세요.
        postReader.validatePostExist(commentCreateCommand.postId());
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