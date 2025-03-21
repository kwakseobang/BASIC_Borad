package com.kwakmunsu.board.post.repository;

import com.kwakmunsu.board.post.entity.Post;

public interface PostRepository {

    void append(Post post);
    Post read(Long postId);

}