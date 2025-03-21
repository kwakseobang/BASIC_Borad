package com.kwakmunsu.board.post.repository;


import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public void append(Post post) {
        postJpaRepository.save(post);
    }

    @Override
    public Post read(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST));
    }

}