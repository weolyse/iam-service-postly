package com.postly.iam_service.mapper;

import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.request.CreatePostRequest;
import com.postly.iam_service.model.request.UpdatePostRequest;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface PostMapper {

//    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDto toPostDto(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "created", ignore = true)
    Post toPost(CreatePostRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "created", ignore = true)
    void updatePost(@MappingTarget Post post, UpdatePostRequest request);
}
