package com.postly.iam_service.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private static final List<String> posts = new ArrayList<>();

    @Override
    public void createPost(String post) {
        posts.add(post);
    }
}
