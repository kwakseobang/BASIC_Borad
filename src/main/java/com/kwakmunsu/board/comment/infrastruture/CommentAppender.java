package com.kwakmunsu.board.comment.infrastruture;


import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentAppender {

    private final CommentRepository commentRepository;

    public void append(String content, Long postId, Long writerId) {
        Comment comment = Comment.builder()
                .content(content)
                .postId(postId)
                .writerId(writerId)
                .build();
        commentRepository.append(comment);
    }

}