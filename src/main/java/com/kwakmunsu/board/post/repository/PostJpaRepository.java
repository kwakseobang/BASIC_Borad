package com.kwakmunsu.board.post.repository;

import com.kwakmunsu.board.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

}