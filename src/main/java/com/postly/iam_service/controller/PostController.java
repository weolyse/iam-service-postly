package com.postly.iam_service.controller;

import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.request.CreatePostRequest;
import com.postly.iam_service.model.request.UpdatePostRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<IamResponse<PostDto>> getPostById(@PathVariable("id") Integer id) {
        IamResponse<PostDto> response = postService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<IamResponse<PostDto>> createPost(@RequestBody @Valid CreatePostRequest request) {
        IamResponse<PostDto> response = postService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IamResponse<PostDto>> updatePost(
            @PathVariable(name = "id") Integer id,
            @RequestBody @Valid UpdatePostRequest request
    ) {
        IamResponse<PostDto> iamResponse = postService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(iamResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        postService.softDelete(id);
        return ResponseEntity.ok().build();
    }
}
