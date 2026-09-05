package com.postly.iam_service.service.impl;

import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.exception.NotFoundException;
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

    @Override
    public IamResponse<PostDto> findById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(id)));

        PostDto dto = PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .likes(post.getLikes())
                .created(post.getCreated())
                .build();
        return IamResponse.createSuccessful(dto);
    }
}
