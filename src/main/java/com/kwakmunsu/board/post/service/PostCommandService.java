package com.kwakmunsu.board.post.service;

import com.kwakmunsu.board.comment.service.CommentCommandService;
import com.kwakmunsu.board.favoritespost.service.FavoritesCommandService;
import com.kwakmunsu.board.global.exception.ForbiddenException;
import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.likes.service.LikesCommandService;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.service.MemberQueryService;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.service.dto.request.PostCreateServiceRequest;
import com.kwakmunsu.board.post.service.dto.request.PostUpdateServiceRequest;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class PostCommandService {

    private final FavoritesCommandService favoritesPostService;
    private final CommentCommandService commentCommandService;
    private final PostRepository postRepository;
    private final LikesCommandService likesCommandService;
    private final MemberQueryService memberQueryService;

    public Long create(Long memberId, PostCreateServiceRequest request) {
        Member member = memberQueryService.getMember(memberId);
        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .writer(member)
                .build();
        return postRepository.save(post); // postId 값만 반환합니다.
    }

    @Transactional
    public void update(Long postId, Long memberId, PostUpdateServiceRequest request) {
        validateAccess(postId, memberId);

        Post post = postRepository.findById(postId);
        post.updatePost(request.title(), request.content());
    }

    @Transactional
    public void updateViews(Long postId) {
        Post post = postRepository.findById(postId);
        post.incrementViewCount();
    }

    @Transactional
    public void delete(Long postId, Long memberId) {
        validateAccess(postId, memberId);
        likesCommandService.unlikePost(postId, memberId);
        commentCommandService.deleteAll(postId);
        favoritesPostService.cancel(postId, memberId);
        postRepository.deleteById(postId);
    }

    public void validatePostExist(Long postId) {
        if (postRepository.isExist(postId)) {
            return;
        }
        throw new NotFoundException((ErrorCode.NOT_FOUND_POST));
    }

    private void validateAccess(Long postId, Long memberId) {
        if (postRepository.existsByIdAndWriterId(postId, memberId)) {
            return;
        }
        throw new ForbiddenException(ErrorCode.FORBIDDEN_ERROR);
    }

}