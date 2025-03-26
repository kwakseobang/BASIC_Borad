package com.kwakmunsu.board.favoritespost.service;

import static com.kwakmunsu.board.post.entity.PostSortOption.toSortOption;

import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import com.kwakmunsu.board.post.entity.PostResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.repository.CursorServiceRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FavoritesQueryService {

    private final FavoritesPostRepository favoritesPostRepository;

    public List<PostResponse> findAll(
            CursorServiceRequest request,
            String option,
            Long memberId
    ) {
        PostSortOption sortOption = toSortOption(option);
        return favoritesPostRepository.findAll(request, sortOption, memberId);
    }

}