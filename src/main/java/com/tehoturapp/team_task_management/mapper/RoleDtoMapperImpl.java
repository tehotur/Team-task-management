package com.tehoturapp.team_task_management.mapper;

import com.tehoturapp.team_task_management.dto.RoleDto;
import com.tehoturapp.team_task_management.persistence.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RoleDtoMapperImpl implements DtoMapper<Role, RoleDto> {
    @Override
    public Role toEntity(RoleDto roleDto) {
        if (roleDto == null){
            return null;
        }
        return new Role(
                roleDto.getId(),
                roleDto.getName(),
                new ArrayList<>()
        );
    }

    @Override
    public RoleDto toDto(Role role) {
        if (role == null){
            return null;
        }
        return new RoleDto(
                role.getId(),
                role.getName()
        );
    }
}
