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
    public Long save(Comment comment) {
        Comment saved = commentJpaRepository.save(comment);
        return saved.getId();
    }

    @Override
    public Comment findById(Long commentId) {
        return commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_COMMENT));
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return commentJpaRepository.findByPostId(postId);
    }

    @Override
    public void deleteById(Long commentId) {
        commentJpaRepository.deleteById(commentId);
    }

    @Override
    public void deleteAllByPostId(Long postId) {
        commentJpaRepository.deleteByPostId(postId);
    }

    @Override
    public void validateByCommentId(Long commentId) {
        if (commentJpaRepository.existsById(commentId)) {
            return;
        }
        throw new NotFoundException(ErrorCode.NOT_FOUND_COMMENT);
    }

    @Override
    public boolean isExistByPostId(Long postId) {
        return commentJpaRepository.existsByPostId(postId);
    }

    @Override
    public boolean existsByIdAndWriterId(Long commentId, Long writerId) {
        return commentJpaRepository.existsByIdAndWriterId(commentId, writerId);
    }

}