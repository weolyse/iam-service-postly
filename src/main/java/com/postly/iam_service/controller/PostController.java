package com.postly.iam_service.controller;

import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.request.PostRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<IamResponse<PostDto>> createPost(@RequestBody @Valid PostRequest request) {
        IamResponse<PostDto> response = postService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
