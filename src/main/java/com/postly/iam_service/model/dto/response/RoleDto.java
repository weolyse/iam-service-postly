package com.postly.iam_service.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class RoleDto implements Serializable {

    private Integer id;
    private String name;

}
