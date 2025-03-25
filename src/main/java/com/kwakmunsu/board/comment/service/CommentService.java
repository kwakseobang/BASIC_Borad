package com.kwakmunsu.board.comment.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.service.dto.request.CommentCreateServiceRequest;
import com.kwakmunsu.board.comment.service.dto.request.CommentUpdateServiceRequest;
import com.kwakmunsu.board.comment.service.dto.response.CommentResponse;
import com.kwakmunsu.board.comment.service.repository.CommentRepository;
import com.kwakmunsu.board.global.exception.ForbiddenException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.post.service.PostCommandService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostCommandService postCommandService;
    private final CommentRepository commentRepository;



}