package com.kwakmunsu.board.post.controller;

import static com.kwakmunsu.board.global.response.ResponseData.success;

import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.post.controller.dto.PostCreateRequest;
import com.kwakmunsu.board.post.controller.dto.PostUpdateRequest;
import com.kwakmunsu.board.post.entity.PostDetailResponse;
import com.kwakmunsu.board.post.entity.PostResponse;
import com.kwakmunsu.board.post.repository.CursorServiceRequest;
import com.kwakmunsu.board.post.service.PostCommandService;
import com.kwakmunsu.board.post.service.PostQueryService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
@RestController
public class PostController implements PostDocsController {

    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    @PostMapping
    public ResponseEntity<ResponseData<Long>> create(
            @CurrentLoginMember Long memberId,
            @Valid @RequestBody PostCreateRequest request
    ) {
        Long postId = postCommandService.create(memberId, request.toServiceRequest());
        return success(SuccessCode.CREATED_POST, postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResponseData<PostDetailResponse>> read(
            @PathVariable("postId") Long postId
    ) {
        PostDetailResponse postDetailResponse = postQueryService.read(postId);
        return success(SuccessCode.READ_POST, postDetailResponse);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<PostResponse>>> findAll(
            @RequestParam(value = "lastPostId", required = false) Long lastPostId,
            @RequestParam(value = "lastViews", required = false) Long lastViews,
            @RequestParam(value = "lastTitle", required = false) String lastTitle,
            @RequestParam(value = "lastCreatedAt", required = false) LocalDateTime lastCreatedAt,
            @RequestParam(defaultValue = "createAt", value = "sortBy") String sortBy
    ) {
        CursorServiceRequest request = new CursorServiceRequest(
                lastPostId,
                lastViews,
                lastTitle,
                lastCreatedAt
        );
        return success(SuccessCode.READ_POST_LIST, postQueryService.findAll(request, sortBy));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> update(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId,
            @Valid @RequestBody PostUpdateRequest request
    ) {
        postCommandService.update(postId, memberId, request.toServiceRequest());
        return success(SuccessCode.UPDATE_POST);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> delete(
            @PathVariable("postId") Long postId,
            @CurrentLoginMember Long memberId
    ) {
        postCommandService.delete(postId, memberId);
        return success(SuccessCode.DELETE_POST);
    }

    @PostMapping("/{postId}/views")
    public ResponseEntity<ResponseData<?>> updateViews(
            @PathVariable("postId") Long postId
    ) {
        postCommandService.updateViews(postId);
        return success(SuccessCode.UPDATE_VIEWS);
    }

    @GetMapping("/{postId}/views")
    public ResponseEntity<ResponseData<Long>> readViews(
            @PathVariable("postId") Long postId
    ) {
        Long viewsCount = postQueryService.readViews(postId);
        return success(SuccessCode.READ_VIEWS, viewsCount);
    }

}