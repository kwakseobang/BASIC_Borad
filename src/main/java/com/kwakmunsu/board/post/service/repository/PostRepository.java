package com.kwakmunsu.board.post.service.repository;

import com.kwakmunsu.board.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository {

    long append(Post post);
    void deleteById(Long postId);
    Post read(Long postId);
    boolean isExist(Long postId);
    Page<Post> readAll(Pageable pageable);

}