package com.postly.iam_service.controller;

import com.postly.iam_service.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Map<String, Object> requestBody) {
        String title = (String) requestBody.get("title");
        String content = (String) requestBody.get("content");
        String post = title + content;
        postService.createPost(post);
        return new ResponseEntity<>("Created post with title" + title, HttpStatus.CREATED);
    }

}
