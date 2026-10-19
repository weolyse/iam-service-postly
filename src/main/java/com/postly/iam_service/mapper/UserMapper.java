package com.postly.iam_service.mapper;

import com.postly.iam_service.model.dto.response.UserDto;
import com.postly.iam_service.model.dto.response.UserProfileDto;
import com.postly.iam_service.model.entity.RegistrationStatus;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.dto.request.authentication.RegistrationRequest;
import com.postly.iam_service.model.dto.request.user.CreateUserRequest;
import com.postly.iam_service.model.dto.request.user.UpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        imports = {RegistrationStatus.class, RoleMapper.class}
)
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(RoleMapper.toListRoleDto(user.getRoles()))")
    UserDto toUserDto(User user);

    @Mapping(target = "registrationStatus", expression = "java(RegistrationStatus.ACTIVE)")
    User createUser(CreateUserRequest request);

    void update(@MappingTarget User user, UpdateUserRequest request);

    @Mapping(target = "roles", expression = "java(RoleMapper.toListRoleDto(user.getRoles()))")
    @Mapping(target = "token", source = "accessToken")
    UserProfileDto toUserProfileDto(User user, String accessToken, String refreshToken);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "registrationStatus", expression = "java(RegistrationStatus.ACTIVE)")
    User toUser(RegistrationRequest request);
}
