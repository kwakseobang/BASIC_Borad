package com.kwakmunsu.board.comment.entity;

import com.kwakmunsu.board.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "comment")
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "writer_id", nullable = false)
    private Long writerId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    private Comment(Long postId, Long writerId, String content) {
        this.postId = postId;
        this.writerId = writerId;
        this.content = content;
    }

    public void updateComment(String content) {
        this.content = content;
    }

}