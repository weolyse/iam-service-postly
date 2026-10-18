package com.postly.iam_service.model.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CreateUserRequest implements Serializable {

    @NotBlank(message = "Username cannot be blank!")
    private final String username;

    @NotBlank(message = "Password cannot be blank!")
    private final String password;

    @NotBlank(message = "Email cannot be blank!")
    private final String email;

}
