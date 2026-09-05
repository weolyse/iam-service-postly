package com.postly.iam_service.controller;

import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
