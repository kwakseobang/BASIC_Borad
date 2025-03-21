package com.kwakmunsu.board.post.repository;


import com.kwakmunsu.board.global.exception.BadRequestException;
import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void deleteById(Long postId) {
        postJpaRepository.deleteById(postId);
    }

    @Override
    public Post read(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST));
    }

    @Override
    public Page<Post> readAll(Pageable pageable) {
        try {
            return postJpaRepository.findAll(pageable);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorCode.FAILED_PAGING);
        }
    }

    @Override
    public boolean isExist(Long postId) {
        return postJpaRepository.existsById(postId);
    }
}