package com.kwakmunsu.board.comment.repository;

import com.kwakmunsu.board.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

}