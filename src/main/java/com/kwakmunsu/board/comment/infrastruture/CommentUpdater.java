package com.kwakmunsu.board.comment.infrastruture;


import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentUpdater {

    private final CommentRepository commentRepository;

    @Transactional
    public void update(String newContent, Long commentId) {
        Comment comment = commentRepository.readById(commentId);
        comment.updateComment(newContent);
    }

}