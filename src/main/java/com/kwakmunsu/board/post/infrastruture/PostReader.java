package com.kwakmunsu.board.post.infrastruture;


import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostReader {

    private final PostRepository postRepository;

    public Post read(Long postId) {
        return postRepository.read(postId);
    }

    public Page<Post> readAll(int page, int pageSize, String sortBy, Direction direction) {
        Pageable pageable = PageRequest.of(
                page - 1,
                pageSize,
                Sort.by(direction, sortBy)
        );

        return postRepository.readAll(pageable);
    }

    public void validatePostExist(Long postId) {
        if (postRepository.isExist(postId)) {
            return;
        }

        throw new NotFoundException((ErrorCode.NOT_FOUND_POST));
    }

}