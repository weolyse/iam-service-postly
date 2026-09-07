package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.request.CreatePostRequest;
import com.postly.iam_service.model.request.UpdatePostRequest;
import com.postly.iam_service.model.response.IamResponse;

public interface PostService {

    IamResponse<PostDto> findById(Integer id);

    IamResponse<PostDto> create(CreatePostRequest request);

    IamResponse<PostDto> update(Integer id, UpdatePostRequest request);
}
