package com.kwakmunsu.board.comment.repository;


import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public long append(Comment comment) {
        Comment saved = commentJpaRepository.save(comment);
        return saved.getId();
    }

    @Override
    public Comment readById(Long commentId) {
        return commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_COMMENT));
    }

    @Override
    public List<Comment> readByPostId(Long postId) {
        return commentJpaRepository.findByPostId(postId);

    }

    @Override
    public void delete(Long commentId) {
        commentJpaRepository.deleteById(commentId);
    }

    @Override
    public void deleteAll(Long postId) {
        commentJpaRepository.deleteByPostId(postId);
    }

    @Override
    public void validateCommentById(Long commentId) {
        if (commentJpaRepository.existsById(commentId)) {
            return;
        }
        throw new NotFoundException(ErrorCode.NOT_FOUND_COMMENT);
    }

    @Override
    public boolean isExistByPostId(Long postId) {
        return commentJpaRepository.existsByPostId(postId);
    }
}