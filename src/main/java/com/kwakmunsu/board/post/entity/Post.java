package com.kwakmunsu.board.post.entity;


import com.kwakmunsu.board.global.entity.BaseEntity;
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

@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "writer_id")
    private Long writerId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "view_count")
    private long viewCount;

    @Builder
    public Post(Long writerId, String title, String content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.viewCount = 0; // 명시하기 위해 추가함.
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void updatePost(String title, String content) {
        updateTitle(title);
        updateContent(content);
    }

    private void updateTitle(String newTitle) {
        if(title == null || title.isEmpty()) {
            return;
        }
        this.title = newTitle;
    }

    private void updateContent(String newContent) {
        if(newContent == null || newContent.isEmpty()) {
            return;
        }
        this.content = newContent;
    }

}