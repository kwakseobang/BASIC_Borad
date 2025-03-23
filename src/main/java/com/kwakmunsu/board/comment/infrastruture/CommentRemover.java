package com.kwakmunsu.board.comment.infrastruture;


import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentRemover {

    private final CommentRepository commentRepository;

    @Transactional
    public void delete(Long commentId) {
        commentRepository.validateCommentById(commentId);
        commentRepository.delete(commentId);
    }

    @Transactional
    public void deleteAll(Long postId) {
        if (commentRepository.isExistByPostId(postId)) {
            commentRepository.deleteAll(postId);
        }
    }

}