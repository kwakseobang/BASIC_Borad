package com.kwakmunsu.board.post.infrastruture;


import com.kwakmunsu.board.comment.infrastruture.CommentCommander;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostCommander;
import com.kwakmunsu.board.likes.infrastruture.LikesCommander;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class PostCommander {

    private final LikesCommander likesCommander;
    private final FavoritesPostCommander favoritesPostCommander;
    private final CommentCommander commentCommander;
    private final PostRepository postRepository;

    public void append(String title, String content, Long memberId) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .writerId(memberId)
                .build();

        postRepository.append(post);
    }

    @Transactional
    public void delete(Long postId, Long memberId) {
        likesCommander.unLike(postId, memberId);
        commentCommander.deleteAll(postId);
        favoritesPostCommander.cancel(postId, memberId);
        postRepository.deleteById(postId);
    }

}