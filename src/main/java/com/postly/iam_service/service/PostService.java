package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.request.post.CreatePostRequest;
import com.postly.iam_service.model.request.post.SearchPostRequest;
import com.postly.iam_service.model.request.post.UpdatePostRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.model.response.PaginationResponse;
import org.springframework.data.domain.Pageable;

public interface PostService {

    IamResponse<PostDto> findById(Integer id);

    IamResponse<PostDto> create(CreatePostRequest request);

    IamResponse<PostDto> update(Integer id, UpdatePostRequest request);

    void softDelete(Integer id);

    IamResponse<PaginationResponse<PostDto>> findAll(Pageable pageable);

    IamResponse<PaginationResponse<PostDto>> search(SearchPostRequest request, Pageable pageable);
}
