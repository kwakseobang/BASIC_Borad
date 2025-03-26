package com.kwakmunsu.board.post.service.repository;

import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.entity.PostDetailResponse;
import com.kwakmunsu.board.post.entity.PostResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.repository.CursorServiceRequest;
import java.util.List;

public interface PostRepository {

    Long save(Post post);
    void deleteById(Long postId);
    Post findById(Long postId);
    PostDetailResponse read(Long postId);
    boolean isExist(Long postId);
    List<PostResponse> findAll(CursorServiceRequest cursor, PostSortOption postSortOption);
    boolean existsByIdAndWriterId(Long postId, Long memberId);

}