package com.kwakmunsu.board.post.repository;


import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post append(Post post) {
        return postJpaRepository.save(post);
    }

}