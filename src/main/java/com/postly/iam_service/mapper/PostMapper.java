package com.postly.iam_service.mapper;

import com.postly.iam_service.model.dto.PostDto;
import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.request.PostRequest;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Objects;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {DateTimeUtils.class, Objects.class}
)
public interface PostMapper {

    PostDto toPostDto(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "created", ignore = true)
    Post toPost(PostRequest request);
}
