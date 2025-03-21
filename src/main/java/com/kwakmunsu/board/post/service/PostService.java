package com.kwakmunsu.board.post.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostAppender;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import com.kwakmunsu.board.post.service.dto.request.PostCreateCommand;
import com.kwakmunsu.board.post.service.dto.request.PostPageableCommand;
import com.kwakmunsu.board.post.service.dto.request.PostUpdateCommand;
import com.kwakmunsu.board.post.service.dto.response.PostPageResponse;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import com.kwakmunsu.board.post.service.dto.response.PostViewsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostAppender postAppender;
    private final PostReader postReader;
    private final LikesReader likesReader;
    private final CommentReader commentReader;


    public void create(PostCreateCommand postCreateCommand) {
        postAppender.append(
                postCreateCommand.title(),
                postCreateCommand.content(),
                postCreateCommand.memberId()
        );
    }

    public PostResponse read(Long postId) {
        Post post = postReader.read(postId);
        long likesCount = likesReader.readLikes(postId);
        List<Comment> comments = commentReader.readByPostId(postId);

        return PostResponse.from(post, likesCount, comments);
    }

    public PostPageResponse readAll(PostPageableCommand pageableCommand) {
        // Sort 객체를 생성할 때 사용하는 컬럼명은 실제 테이블의 컬럼명이 아니라 Entity 의 속성 이름으로 지정해야 한다.
        Sort sort = Sort.by(Direction.DESC, pageableCommand.sortBy());
        Pageable pageable = PageRequest.of(
                pageableCommand.page() - 1,
                pageableCommand.pageSize(),
                sort
        );

        return new PostPageResponse(postReader.readAll(pageable));
    }


    @Transactional
    public void update(PostUpdateCommand postUpdateCommand) {
        Post post = postReader.read(postUpdateCommand.postId());
        post.updatePost(postUpdateCommand.title(), postUpdateCommand.content());
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