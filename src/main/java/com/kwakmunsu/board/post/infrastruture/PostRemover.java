package com.kwakmunsu.board.post.infrastruture;


import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class PostRemover {

        private final PostRepository postRepository;

        @Transactional
        public void delete(Long postId) {
            postRepository.deleteById(postId);
        }
}