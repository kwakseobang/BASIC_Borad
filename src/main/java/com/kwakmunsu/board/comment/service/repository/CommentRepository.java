package com.kwakmunsu.board.comment.service.repository;

import com.kwakmunsu.board.comment.entity.Comment;
import java.util.List;

public interface CommentRepository {

    Long save(Comment comment);
    Comment findById(Long commentId);
    List<Comment> findByPostId(Long postId);
    void deleteById(Long commentId);
    void deleteAllByPostId(Long postId);
    void validateByCommentId(Long commentId);
    boolean isExistByPostId(Long commentId);
    boolean existsByIdAndWriterId(Long commentId, Long memberId);

}