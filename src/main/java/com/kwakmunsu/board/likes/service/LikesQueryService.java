package com.kwakmunsu.board.likes.service;

import com.kwakmunsu.board.likes.service.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LikesQueryService {

    private final LikesRepository likesRepository;

    public long readLikeCount(Long postId) {
        return likesRepository.countByPostId(postId);
    }

}