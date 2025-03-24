package com.kwakmunsu.board.comment.service.repository;

import com.kwakmunsu.board.comment.entity.Comment;
import java.util.List;

public interface CommentRepository {

    long append(Comment comment);
    Comment readById(Long commentId);
    List<Comment> readByPostId(Long postId);
    void delete(Long commentId);
    void deleteAll(Long postId);
    void validateCommentById(Long commentId);
    boolean isExistByPostId(Long commentId);

}