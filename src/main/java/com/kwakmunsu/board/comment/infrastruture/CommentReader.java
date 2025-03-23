package com.kwakmunsu.board.comment.infrastruture;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentReader {

    private final CommentRepository commentRepository;

    public Comment read(Long commentId) {
        return commentRepository.readById(commentId);
    }

    public List<Comment> readByPostId(Long postId) {
        return commentRepository.readByPostId(postId);
    }

}