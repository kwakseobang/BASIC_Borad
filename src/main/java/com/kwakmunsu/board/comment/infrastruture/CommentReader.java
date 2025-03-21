package com.kwakmunsu.board.comment.infrastruture;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentReader {

    private final CommentRepository commentRepository;

    public Comment read(Long commentId) {
        return commentRepository.read(commentId);
    }

}