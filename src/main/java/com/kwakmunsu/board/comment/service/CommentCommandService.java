package com.kwakmunsu.board.comment.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.dto.request.CommentCreateServiceRequest;
import com.kwakmunsu.board.comment.service.dto.request.CommentUpdateServiceRequest;
import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import com.kwakmunsu.board.global.exception.ForbiddenException;
import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Long create(
            Long memberId,
            CommentCreateServiceRequest request
    ) {
        validatePostExist(request.postId());

        Comment comment = Comment.builder()
                .writerId(memberId)
                .postId(request.postId())
                .content(request.content())
                .build();
        return commentRepository.save(comment);
    }

    @Transactional
    public void update(
            Long commentId,
            Long memberId,
            CommentUpdateServiceRequest request
    ) {
        validateAccess(commentId, memberId);
        Comment comment = commentRepository.findById(commentId);
        comment.updateComment(request.content());
    }

    @Transactional
    public void delete(
            Long commentId,
            Long memberId
    ) {
        validateAccess(commentId, memberId);
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void deleteAll(Long postId) {
        if (commentRepository.isExistByPostId(postId)) {
            commentRepository.deleteAllByPostId(postId);
        }
    }

    private void validatePostExist(Long postId) {
        if (postRepository.isExist(postId)) {
            return;
        }
        throw new NotFoundException((ErrorCode.NOT_FOUND_POST));
    }


    private void validateAccess(Long commentId, Long memberId) {
        if (commentRepository.existsByIdAndWriterId(commentId, memberId)) {
            return;
        }
        throw new ForbiddenException(ErrorCode.FORBIDDEN_ERROR);
    }


}