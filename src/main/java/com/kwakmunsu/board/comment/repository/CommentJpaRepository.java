package com.kwakmunsu.board.comment.repository;

import com.kwakmunsu.board.comment.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    void deleteByPostId(Long postId);
    boolean existsByPostId(Long postId);
}