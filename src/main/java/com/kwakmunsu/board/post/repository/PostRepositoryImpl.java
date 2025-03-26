package com.kwakmunsu.board.post.repository;

import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.entity.PostDetailResponse;
import com.kwakmunsu.board.post.entity.PostResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;
    private final PostQueryRepository postQueryRepository;

    @Override
    public Long save(Post post) {
        Post saved = postJpaRepository.save(post);
        return saved.getId();
    }

    @Override
    public void deleteById(Long postId) {
        postJpaRepository.deleteById(postId);
    }

    @Override
    public Post findById(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST));
    }

    @Override
    public PostDetailResponse read(Long postId) {
        PostDetailResponse postDetailResponse = postQueryRepository.read(postId);
        if (postDetailResponse.postResponse() == null) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_POST);
        }
        return postDetailResponse;
    }

    @Override
    public List<PostResponse> findAll(
            CursorServiceRequest request,
            PostSortOption option
    ) {
        return postQueryRepository.findAll(request, option);
    }

    @Override
    public boolean isExist(Long postId) {
        return postJpaRepository.existsById(postId);
    }

    @Override
    public boolean existsByIdAndWriterId(Long postId, Long memberId) {
        return postJpaRepository.existsByIdAndWriterId(postId, memberId);
    }

}