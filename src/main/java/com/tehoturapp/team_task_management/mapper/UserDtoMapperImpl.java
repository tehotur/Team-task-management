package com.tehoturapp.team_task_management.mapper;

import com.tehoturapp.team_task_management.dto.UserDto;
import com.tehoturapp.team_task_management.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoMapperImpl implements DtoMapper<User, UserDto> {

    private final RoleDtoMapperImpl roleDtoMapper;
    private final TaskListDtoMapperImpl taskListDtoMapper;

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
                taskListDtoMapper.toEntity(userDto.getTaskList())
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
                roleDtoMapper.toDto(user.getRole()),
                taskListDtoMapper.toDto(user.getTaskList())
        );
    }
}
