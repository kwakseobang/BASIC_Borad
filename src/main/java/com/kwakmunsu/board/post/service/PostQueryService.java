package com.kwakmunsu.board.post.service;

import static com.kwakmunsu.board.post.entity.PostSortOption.toSortOption;

import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.entity.dto.PostDetailResponse;
import com.kwakmunsu.board.post.entity.dto.PostResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.service.dto.request.CursorServiceRequest;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Component
public class PostQueryService {

    private final PostRepository postRepository;

    public PostDetailResponse read(Long postId) {
        return postRepository.read(postId);
    }

    public List<PostResponse> findAll(CursorServiceRequest cursor, String option) {
        PostSortOption sortOption = toSortOption(option);
        return postRepository.findAll(cursor, sortOption);
    }

    public Long readViews(Long postId) {
        Post post = postRepository.findById(postId);
        return post.getViewCount();
    }

}