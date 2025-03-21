package com.kwakmunsu.board.comment.infrastruture;


import com.kwakmunsu.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentRemover {

    private final CommentRepository commentRepository;

    public void delete(Long commentId) {
        commentRepository.validateCommentExists(commentId);
        commentRepository.delete(commentId);
    }

}