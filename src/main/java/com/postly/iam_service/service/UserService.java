package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.UserDto;
import com.postly.iam_service.model.request.user.UpdateUserRequest;
import com.postly.iam_service.model.request.user.CreateUserRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.model.response.PaginationResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {

    IamResponse<UserDto> findById(Integer id);

    IamResponse<UserDto> create(CreateUserRequest request);

    IamResponse<UserDto> update(Integer userId, UpdateUserRequest request);

    void softDelete(Integer userId);

    IamResponse<PaginationResponse<UserDto>> findAll(Pageable pageable);

}
