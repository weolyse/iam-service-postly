package com.postly.iam_service.controller;

import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.constant.ApiLogMessage;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.repository.PostRepository;
import com.postly.iam_service.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id) {
        log.info(ApiLogMessage.POST_INFO_BY_ID.getMessage(id));
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.info(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(id));
                    return ResponseEntity.notFound().build();
                });
    }

}
