package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.response.IamResponse;

public interface PostService {

    IamResponse<PostDto> findById(Integer id);
}
