package com.kwakmunsu.board.post.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.comment.service.dto.response.CommentPageResponse;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostReader;
import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.infrastruture.MemberReader;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostCommander;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import com.kwakmunsu.board.post.service.dto.request.PostCreateCommand;
import com.kwakmunsu.board.post.service.dto.request.PostDeleteCommand;
import com.kwakmunsu.board.post.service.dto.request.PostPageableCommand;
import com.kwakmunsu.board.post.service.dto.request.PostUpdateCommand;
import com.kwakmunsu.board.post.service.dto.response.PostDetailResponse;
import com.kwakmunsu.board.post.service.dto.response.PostPageResponse;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import com.kwakmunsu.board.post.service.dto.response.PostViewsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public void create(PostCreateCommand postCreateCommand) {
        Member member = memberReader.getMember(postCreateCommand.memberId());

        postCommander.append(
                postCreateCommand.title(),
                postCreateCommand.content(),
                member
        );
    }

    public PostDetailResponse read(Long postId) {
        Post post = postReader.read(postId);
        long likesCount = likesReader.readLikes(postId);
        long favoritesCount = favoritesPostReader.countByPostId(postId);
        PostResponse postResponse = PostResponse.from(post, likesCount, favoritesCount);

        List<Comment> comments = commentReader.readByPostId(postId);
        List<CommentPageResponse> commentPageResponses = comments.stream()
                .map(CommentPageResponse::from)
                .toList();

        return new PostDetailResponse(postResponse, commentPageResponses);
    }

    public PostPageResponse readAll(PostPageableCommand pageableCommand) {
        Pageable pageable = PageRequest.of(
                pageableCommand.page() - 1,
                pageableCommand.pageSize(),
                Sort.by(Direction.DESC, pageableCommand.sortBy())
        );
        Page<Post> posts = postReader.readAll(pageable);

        return new PostPageResponse(posts);
    }

    @Transactional
    public void update(PostUpdateCommand postUpdateCommand) {
        Post post = postReader.read(postUpdateCommand.postId());
        post.updatePost(postUpdateCommand.title(), postUpdateCommand.content());
    }

    public void delete(PostDeleteCommand postDeleteCommand) {
        Long postId = postDeleteCommand.postId();
        Long memberId = postDeleteCommand.memberId();

        postReader.validatePostExist(postId);
        postCommander.delete(postId, memberId);
    }

    @Transactional
    public void updateViews(Long postId) {
        Post post = postReader.read(postId);
        post.incrementViewCount();
    }

    public PostViewsResponse readViews(Long postId) {
        Post post = postReader.read(postId);

        return new PostViewsResponse(post.getViewCount());
    }

}