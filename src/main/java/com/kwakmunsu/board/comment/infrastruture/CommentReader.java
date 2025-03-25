package com.kwakmunsu.board.comment.infrastruture;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import com.kwakmunsu.board.global.exception.ForbiddenException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentReader {

    private final CommentRepository commentRepository;

    public Comment read(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> readByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public void validateAccess(Long commentId, Long memberId) {
        if (commentRepository.existsByIdAndWriterId(commentId, memberId)) {
            return;
        }
        throw new ForbiddenException(ErrorCode.FORBIDDEN_ERROR);
    }

}