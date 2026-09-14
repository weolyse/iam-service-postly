package com.postly.iam_service.mapper;

import com.postly.iam_service.model.dto.UserDto;
import com.postly.iam_service.model.entity.RegistrationStatus;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.request.user.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        imports = {RegistrationStatus.class}
)
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "registrationStatus", expression = "java(RegistrationStatus.ACTIVE)")
    User toUser(CreateUserRequest request);
}
