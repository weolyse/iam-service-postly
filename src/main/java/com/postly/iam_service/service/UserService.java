package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.UserDto;
import com.postly.iam_service.model.request.user.CreateUserRequest;
import com.postly.iam_service.model.response.IamResponse;

public interface UserService {

    IamResponse<UserDto> findById(Integer id);

    IamResponse<UserDto> create(CreateUserRequest request);

}
