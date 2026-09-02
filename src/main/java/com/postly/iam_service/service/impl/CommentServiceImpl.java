package com.postly.iam_service.service.impl;

import com.postly.iam_service.service.CommentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class CommentServiceImpl implements CommentService {

    private static final List<String> comments = new ArrayList<>();

    public void createComment(String comment) {
        comments.add(comment);
        System.out.println(comment);
    }
}
