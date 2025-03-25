package com.kwakmunsu.board.post.service;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.CommentCommandService;
import com.kwakmunsu.board.comment.service.CommentQueryService;
import com.kwakmunsu.board.comment.service.dto.response.CommentPreviewResponse;
import com.kwakmunsu.board.favoritespost.service.FavoritesCommandService;
import com.kwakmunsu.board.favoritespost.service.FavoritesQueryService;
import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import com.kwakmunsu.board.likes.service.LikesQueryService;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.service.dto.request.PostPageableServiceRequest;
import com.kwakmunsu.board.post.service.dto.response.PostPreviewResponse;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class PostQueryService {

    private final PostRepository postRepository;
    private final LikesQueryService likesQueryService;
//    private final FavoritesQueryService favoritesQueryService;
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

    public List<PostPreviewResponse> readAll(PostPageableServiceRequest request) {
        Direction direction = request.isDesc() ? DESC : ASC;
        Page<Post> posts = findAll(request, direction);

        List<PostPreviewResponse> postPreviewResponses = new ArrayList<>();
        for (Post post : posts) {
            long likesCount = PostQueryService.this.likesQueryService.readLikeCount(post.getId());
            long favoritesCount = favoritesPostRepository.countByPostId(post.getId());
            postPreviewResponses.add(PostPreviewResponse.from(post, likesCount, favoritesCount));
        }
        return postPreviewResponses;
    }

    public Long readViews(Long postId) {
        Post post = postRepository.findById(postId);
        return post.getViewCount();
    }

    public Post readById(Long postId) {
        return postRepository.findById(postId);
    }

    private Page<Post> findAll(PostPageableServiceRequest request, Direction direction) {
        Pageable pageable = PageRequest.of(
                request.page() - 1,
                request.pageSize(),
                Sort.by(direction, request.sortBy())
        );
        return postRepository.findAll(pageable);
    }

}