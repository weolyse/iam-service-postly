package com.postly.iam_service.service.impl;

import com.postly.iam_service.mapper.PostMapper;
import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.exception.InvalidDataException;
import com.postly.iam_service.model.exception.NotFoundException;
import com.postly.iam_service.model.request.CreatePostRequest;
import com.postly.iam_service.model.request.UpdatePostRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.repository.PostRepository;
import com.postly.iam_service.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public IamResponse<PostDto> findById(Integer id) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(id)));

        PostDto dto = postMapper.toPostDto(post);
        return IamResponse.createSuccessful(dto);
    }

    @Override
    public IamResponse<PostDto> create(CreatePostRequest request) {
        if (postRepository.existsByTitle(request.getTitle())) {
            throw new InvalidDataException(ApiErrorMessage.DUPLICATE_TITLE.getMessage(request.getTitle()));
        }

        Post postToSave = postMapper.toPost(request);
        Post savedPost = postRepository.save(postToSave);
        PostDto postDto = postMapper.toPostDto(savedPost);
        return IamResponse.createSuccessful(postDto);
    }

    @Override
    public IamResponse<PostDto> update(Integer id, UpdatePostRequest request) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(id)));

        if (postRepository.existsByTitle(request.getTitle()) && !request.getTitle().equals(post.getTitle())) {
            throw new InvalidDataException(ApiErrorMessage.DUPLICATE_TITLE.getMessage(request.getTitle()));
        }

        postMapper.updatePost(post, request);
        post.setUpdated(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        PostDto postDto = postMapper.toPostDto(savedPost);
        return IamResponse.createSuccessful(postDto);
    }

    @Override
    public void softDelete(Integer id) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(id)));

        post.setDeleted(true);
        postRepository.save(post);
    }
}
