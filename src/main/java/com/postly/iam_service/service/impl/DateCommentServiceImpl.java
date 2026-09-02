package com.postly.iam_service.service.impl;

import com.postly.iam_service.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("advancedCommentService")
public class DateCommentServiceImpl implements CommentService {

    private static final List<String> comments = new ArrayList<>();

    @Override
    public void createComment(String comment) {
        String content = "[" + LocalDateTime.now() + "] " + comment;
        comments.add(content);
        System.out.println(content);
    }
}
