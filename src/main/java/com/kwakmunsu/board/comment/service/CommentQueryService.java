package com.kwakmunsu.board.comment.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentQueryService {

    private final CommentRepository commentRepository;

    public CommentResponse read(Long commentId) {
        Comment comment = commentRepository.findById(commentId);
        return CommentResponse.from(comment);
    }

    public List<Comment> readByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

}