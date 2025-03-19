package com.kwakmunsu.board.view.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ViewRepositoryImpl implements ViewRepository {

    private final ViewJpaRepository viewJpaRepository;

}