package com.postly.iam_service.service.impl;

import com.postly.iam_service.mapper.PostMapper;
import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.exception.InvalidDataException;
import com.postly.iam_service.model.exception.NotFoundException;
import com.postly.iam_service.model.request.PostRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.repository.PostRepository;
import com.postly.iam_service.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public IamResponse<PostDto> findById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(id)));

        PostDto dto = postMapper.toPostDto(post);
        return IamResponse.createSuccessful(dto);
    }

    @Override
    public IamResponse<PostDto> create(PostRequest request) {
        if (postRepository.existsByTitle(request.getTitle())) {
            throw new InvalidDataException(ApiErrorMessage.DUPLICATE_TITLE.getMessage(request.getTitle()));
        }

        Post postToSave = postMapper.toPost(request);
        Post savedPost = postRepository.save(postToSave);
        PostDto postDto = postMapper.toPostDto(savedPost);
        return IamResponse.createSuccessful(postDto);
    }
}
