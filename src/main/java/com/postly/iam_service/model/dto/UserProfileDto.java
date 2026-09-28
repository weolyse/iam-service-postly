package com.postly.iam_service.model.dto;

import com.postly.iam_service.model.entity.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class UserProfileDto implements Serializable {

    private Integer id;
    private String username;
    private String email;

    private String registrationStatus;
    private LocalDateTime lastLogin;

    private String token;
    private List<RoleDto> roles;

}
