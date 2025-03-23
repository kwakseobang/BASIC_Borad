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

    public void append(String content, Long postId, Long writerId) {
        Comment comment = Comment.builder()
                .content(content)
                .postId(postId)
                .writerId(writerId)
                .build();
        commentRepository.append(comment);
    }

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

    @Transactional
    public void update(String newContent, Long commentId) {
        Comment comment = commentRepository.readById(commentId);
        comment.updateComment(newContent);
    }

}