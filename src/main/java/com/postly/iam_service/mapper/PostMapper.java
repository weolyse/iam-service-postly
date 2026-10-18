package com.postly.iam_service.mapper;

import com.postly.iam_service.model.dto.response.PostDto;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.dto.request.post.CreatePostRequest;
import com.postly.iam_service.model.dto.request.post.UpdatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

//    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.username", target = "createdBy")
    PostDto toPostDto(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target="user", source="user")
    @Mapping(target="updated", ignore = true)
    @Mapping(target="deleted", ignore = true)
    Post toPost(CreatePostRequest request, User user);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "likes", ignore = true)
//    @Mapping(target = "created", ignore = true)
    void updatePost(@MappingTarget Post post, UpdatePostRequest request);
}
