package com.postly.iam_service.controller;

import com.postly.iam_service.service.CommentService;
import com.postly.iam_service.service.impl.DateCommentServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService defaultService;
    private final CommentService advancedService;

    public CommentController(
            CommentService defaultService,
            @Qualifier("advancedCommentService") CommentService advancedService) {
        this.defaultService = defaultService;
        this.advancedService = advancedService;
    }

    @PostMapping("/createDefault")
    public ResponseEntity<String> createDefaultComment(@RequestBody Map<String, Object> requestBody) {
        String content = (String) requestBody.get("content");
        defaultService.createComment(content);
        return new ResponseEntity<>("Created new comment", HttpStatus.CREATED);
    }

    @PostMapping("/createAdvanced")
    public ResponseEntity<String> addCommentWithDate(@RequestBody Map<String, Object> requestBody) {
        String content = (String) requestBody.get("content");
        advancedService.createComment(content);
        return new ResponseEntity<>("Created new comment with date", HttpStatus.CREATED);
    }

}
