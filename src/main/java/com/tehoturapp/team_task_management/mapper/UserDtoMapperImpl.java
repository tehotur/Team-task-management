package com.tehoturapp.team_task_management.mapper;

import com.tehoturapp.team_task_management.dto.RoleDto;
import com.tehoturapp.team_task_management.dto.UserDto;
import com.tehoturapp.team_task_management.persistence.entity.Role;
import com.tehoturapp.team_task_management.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
                userDto.getRoles()
                        .stream()
                        .map(roleDtoMapper::toEntity)
                        .collect(Collectors.toSet()),
                null
        );
    }

    @Override
    public UserDto toDto(User user) {
        if (user == null){
            return null;
        }

        UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setName(user.getName());
                userDto.setEmail(user.getEmail());
                userDto.setRoles(user.getRoles()
                        .stream()
                        .map(roleDtoMapper::toDto)
                        .collect(Collectors.toSet()));
        return userDto;

        /*        user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
                        .stream()
                        .map(roleDtoMapper::toDto)
                        .collect(Collectors.toSet())
        );*/
    }
}
