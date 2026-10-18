package com.postly.iam_service.mapper;

import com.postly.iam_service.model.dto.response.RoleDto;
import com.postly.iam_service.model.entity.Role;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    static RoleDto toRoleDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }

    static List<RoleDto> toListRoleDto(Collection<Role> roles) {
        return roles.stream()
                .map(RoleMapper::toRoleDto)
                .toList();
    }

}
