package com.kwakmunsu.board.post.service;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.comment.service.dto.response.CommentPreviewResponse;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostReader;
import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.infrastruture.MemberReader;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostCommander;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import com.kwakmunsu.board.post.service.dto.request.PostCreateServiceRequest;
import com.kwakmunsu.board.post.service.dto.request.PostPageableServiceRequest;
import com.kwakmunsu.board.post.service.dto.request.PostUpdateServiceRequest;
import com.kwakmunsu.board.post.service.dto.response.PostPreviewResponse;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostCommander postCommander;
    private final PostReader postReader;
    private final LikesReader likesReader;
    private final CommentReader commentReader;
    private final MemberReader memberReader;
    private final FavoritesPostReader favoritesPostReader;

    public Long create(Long memberId, PostCreateServiceRequest request) {
        Member member = memberReader.getMember(memberId);
        return postCommander.append(
                request.title(),
                request.content(),
                member
        );
    }

    public PostResponse read(Long postId) {
        Post post = postReader.read(postId);
        long likesCount = likesReader.readLikeCount(postId);
        long favoritesCount = favoritesPostReader.countByPostId(postId);

        List<Comment> comments = commentReader.readByPostId(postId);
        List<CommentPreviewResponse> commentPreviewResponses = comments.stream()
                .map(CommentPreviewResponse::from)
                .toList();
        return PostResponse.from(post, likesCount, favoritesCount, commentPreviewResponses);
    }

    public List<PostPreviewResponse> readAll(PostPageableServiceRequest request) {
        // 간단한 삼항 연산자라 사용함.
        Direction direction = request.isDesc() ? DESC : ASC;
        Page<Post> posts = postReader.readAll(
                request.page(),
                request.pageSize(),
                request.sortBy(),
                direction
        );

        List<PostPreviewResponse> postPreviewResponses = new ArrayList<>();
        for (Post post : posts) {
            long likesCount = likesReader.readLikeCount(post.getId());
            long favoritesCount = favoritesPostReader.countByPostId(post.getId());
            postPreviewResponses.add(PostPreviewResponse.from(post, likesCount, favoritesCount));
        }
        return postPreviewResponses;
    }

    @Transactional
    public void update(Long postId, Long memberId, PostUpdateServiceRequest request) {
        postReader.validateAccess(postId, memberId);

        Post post = postReader.read(postId);
        post.updatePost(request.title(), request.content());
    }

    public void delete(Long postId, Long memberId) {
        postReader.validateAccess(postId, memberId);
        postCommander.delete(postId, memberId);
    }

    @Transactional
    public void updateViews(Long postId) {
        Post post = postReader.read(postId);
        post.incrementViewCount();
    }

    public Long readViews(Long postId) {
        Post post = postReader.read(postId);
        return post.getViewCount();
    }

}