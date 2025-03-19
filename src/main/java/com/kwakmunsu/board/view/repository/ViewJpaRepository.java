package com.kwakmunsu.board.view.repository;

import com.kwakmunsu.board.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewJpaRepository extends JpaRepository<Post, Long> {

}