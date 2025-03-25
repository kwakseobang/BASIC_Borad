package com.kwakmunsu.board.post.controller;

import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.post.controller.dto.PostCreateRequest;
import com.kwakmunsu.board.post.controller.dto.PostUpdateRequest;
import com.kwakmunsu.board.post.service.PostService;
import com.kwakmunsu.board.post.service.dto.request.PostDeleteCommand;
import com.kwakmunsu.board.post.service.dto.request.PostPageableCommand;
import com.kwakmunsu.board.post.service.dto.response.PostPreviewResponse;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class PostController implements PostApiController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ResponseData<Long>> create(
            @Valid @RequestBody PostCreateRequest request
    ) {
        Long postId = postService.create(request.toPostCreateCommand());

        return ResponseData.success(SuccessCode.CREATED_POST, postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResponseData<PostResponse>> read(
            @PathVariable("postId") Long postId
    ) {
        PostResponse postResponse = postService.read(postId);

        return ResponseData.success(SuccessCode.READ_POST, postResponse);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<PostPreviewResponse>>> readAll(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "createdAt")
            String sortBy,
            @RequestParam("isDesc") boolean isDesc
    ) {
        List<PostPreviewResponse> postPreviewResponse = postService.readAll(
                new PostPageableCommand(page, pageSize, sortBy, isDesc)
        );
        return ResponseData.success(SuccessCode.READ_POST_LIST, postPreviewResponse);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> update(
            @PathVariable("postId") Long postId,
            @Valid @RequestBody PostUpdateRequest request
    ) {
        postService.update(request.toPostUpdateCommand(postId));

        return ResponseData.success(SuccessCode.UPDATE_POST);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseData<?>> delete(@PathVariable("postId") Long postId) {
        postService.delete(PostDeleteCommand.from(postId));

        return ResponseData.success(SuccessCode.DELETE_POST);
    }

    @PostMapping("/{postId}/views")
    public ResponseEntity<ResponseData<?>> updateViews(@PathVariable("postId") Long postId) {
        postService.updateViews(postId);

        return ResponseData.success(SuccessCode.UPDATE_VIEWS);
    }

    @GetMapping("/{postId}/views")
    public ResponseEntity<ResponseData<Long>> readViews(
            @PathVariable("postId") Long postId
    ) {
        Long viewsCount = postService.readViews(postId);

        return ResponseData.success(SuccessCode.READ_VIEWS, viewsCount);
    }

}