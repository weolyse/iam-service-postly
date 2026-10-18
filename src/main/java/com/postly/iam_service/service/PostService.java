package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.response.PostDto;
import com.postly.iam_service.model.dto.request.post.CreatePostRequest;
import com.postly.iam_service.model.dto.request.post.SearchPostRequest;
import com.postly.iam_service.model.dto.request.post.UpdatePostRequest;
import com.postly.iam_service.model.format.IamResponse;
import com.postly.iam_service.model.format.PaginationResponse;
import org.springframework.data.domain.Pageable;

public interface PostService {

    IamResponse<PostDto> findById(Integer id);

    IamResponse<PostDto> create(Integer userId, CreatePostRequest request);

    IamResponse<PostDto> update(Integer id, UpdatePostRequest request);

    void softDelete(Integer id);

    IamResponse<PaginationResponse<PostDto>> findAll(Pageable pageable);

    IamResponse<PaginationResponse<PostDto>> search(SearchPostRequest request, Pageable pageable);
}
