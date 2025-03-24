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
import com.kwakmunsu.board.post.service.dto.request.PostCreateCommand;
import com.kwakmunsu.board.post.service.dto.request.PostDeleteCommand;
import com.kwakmunsu.board.post.service.dto.request.PostPageableCommand;
import com.kwakmunsu.board.post.service.dto.request.PostUpdateCommand;
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

    public Long create(PostCreateCommand postCreateCommand) {
        Member member = memberReader.getMember(postCreateCommand.memberId());

        return postCommander.append(
                postCreateCommand.title(),
                postCreateCommand.content(),
                member
        );
    }

    public PostResponse read(Long postId) {
        Post post = postReader.read(postId);
        long likesCount = likesReader.readLikes(postId);
        long favoritesCount = favoritesPostReader.countByPostId(postId);
        List<Comment> comments = commentReader.readByPostId(postId);
        List<CommentPreviewResponse> commentPreviewResponses = comments.stream()
                .map(CommentPreviewResponse::from)
                .toList();

        return PostResponse.from(post, likesCount, favoritesCount, commentPreviewResponses);
    }

    public List<PostPreviewResponse> readAll(PostPageableCommand pageableCommand) {
        // 간단한 삼항 연산자라 사용함.
        Direction direction = pageableCommand.isDesc() ? DESC : ASC;
        Page<Post> posts = postReader.readAll(
                pageableCommand.page(),
                pageableCommand.pageSize(),
                pageableCommand.sortBy(),
                direction
        );

        List<PostPreviewResponse> postPreviewResponses = new ArrayList<>();
        for (Post post : posts) {
            long likesCount = likesReader.readLikes(post.getId());
            long favoritesCount = favoritesPostReader.countByPostId(post.getId());
            postPreviewResponses.add(PostPreviewResponse.from(post, likesCount, favoritesCount));
        }

        return postPreviewResponses;
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

    public Long readViews(Long postId) {
        Post post = postReader.read(postId);

        return post.getViewCount();
    }

}