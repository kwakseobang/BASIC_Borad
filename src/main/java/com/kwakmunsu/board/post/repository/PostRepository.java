package com.kwakmunsu.board.post.repository;

import com.kwakmunsu.board.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository {

    void append(Post post);
    Post read(Long postId);
    Page<Post> readAll(Pageable pageable);

}