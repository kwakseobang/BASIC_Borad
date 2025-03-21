package com.kwakmunsu.board.comment.repository;

import com.kwakmunsu.board.comment.entity.Comment;
import java.util.List;

public interface CommentRepository {

    void append(Comment comment);
    Comment readById(Long commentId);
    List<Comment> readByPostId(Long postId);
    List<Comment> readAll(Long postId, Long writerId);
    void delete(Long commentId);
    void validateCommentExists(Long commentId);

}