package com.kwakmunsu.board.post.service;

import static com.kwakmunsu.board.post.entity.PostSortOption.toSortOption;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.CommentQueryService;
import com.kwakmunsu.board.comment.service.dto.response.CommentPreviewResponse;
import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import com.kwakmunsu.board.likes.service.LikesQueryService;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.entity.PostPaginationResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.repository.CursorServiceRequest;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
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
    private final LikesQueryService likesQueryService;
    private final FavoritesPostRepository favoritesPostRepository;
    private final CommentQueryService commentQueryService;

    public PostResponse read(Long postId) {
        Post post = postRepository.findById(postId);
        long likesCount = likesQueryService.readLikeCount(postId);
        long favoritesCount = favoritesPostRepository.countByPostId(postId);

        List<Comment> comments = commentQueryService.readByPostId(postId);
        List<CommentPreviewResponse> commentPreviewResponses = comments.stream()
                .map(CommentPreviewResponse::from)
                .toList();
        return PostResponse.from(post, likesCount, favoritesCount, commentPreviewResponses);
    }


    public List<PostPaginationResponse> findAll(CursorServiceRequest cursor, String option) {
        PostSortOption sortOption = toSortOption(option);
        return postRepository.findAll(cursor, sortOption);
    }

    public Long readViews(Long postId) {
        Post post = postRepository.findById(postId);
        return post.getViewCount();
    }

    public Post readById(Long postId) {
        return postRepository.findById(postId);
    }

}