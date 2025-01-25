package com.tehoturapp.team_task_management.mapper;

import com.tehoturapp.team_task_management.dto.RoleDto;
import com.tehoturapp.team_task_management.dto.UserDto;
import com.tehoturapp.team_task_management.persistence.entity.Role;
import com.tehoturapp.team_task_management.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoMapperImpl implements DtoMapper<User, UserDto> {

    private final DtoMapper<Role, RoleDto> roleDtoMapper;

    @Override
    public User toEntity(UserDto userDto) {
        if (userDto == null){
            return null;
        }
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                roleDtoMapper.toEntity(userDto.getRole()),
                null
        );
    }

    @Override
    public UserDto toDto(User user) {
        if (user == null){
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                roleDtoMapper.toDto(user.getRole())
        );
    }
}
