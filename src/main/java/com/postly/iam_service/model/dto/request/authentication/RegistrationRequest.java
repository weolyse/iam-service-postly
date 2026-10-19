package com.postly.iam_service.model.dto.request.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class RegistrationRequest implements Serializable {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

}
