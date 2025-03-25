package com.kwakmunsu.board.comment.infrastruture;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentCommander {

    private final CommentRepository commentRepository;

    public Long append(Long postId, Long writerId, String content) {
        Comment comment = Comment.builder()
                .postId(postId)
                .writerId(writerId)
                .content(content)
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long commentId) {
        commentRepository.validateByCommentId(commentId);
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void deleteAll(Long postId) {
        if (commentRepository.isExistByPostId(postId)) {
            commentRepository.deleteAllByPostId(postId);
        }
    }

    @Transactional
    public void update(Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId);
        comment.updateComment(newContent);
    }

}