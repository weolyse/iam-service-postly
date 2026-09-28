package com.postly.iam_service.mapper;

import com.postly.iam_service.model.dto.RoleDto;
import com.postly.iam_service.model.dto.UserDto;
import com.postly.iam_service.model.dto.UserProfileDto;
import com.postly.iam_service.model.entity.RegistrationStatus;
import com.postly.iam_service.model.entity.Role;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.request.user.CreateUserRequest;
import com.postly.iam_service.model.request.user.UpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        imports = {RegistrationStatus.class, RoleMapper.class}
)
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(RoleMapper.toListRoleDto(user.getRoles()))")
    UserDto toUserDto(User user);

    @Mapping(target = "registrationStatus", expression = "java(RegistrationStatus.ACTIVE)")
    User toUser(CreateUserRequest request);

    void update(@MappingTarget User user, UpdateUserRequest request);

    @Mapping(target = "roles", expression = "java(RoleMapper.toListRoleDto(user.getRoles()))")
    @Mapping(target = "token", source = "token")
    UserProfileDto toUserProfileDto(User user, String token);
}
