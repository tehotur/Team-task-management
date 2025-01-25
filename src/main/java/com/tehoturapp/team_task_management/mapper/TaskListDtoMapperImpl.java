package com.tehoturapp.team_task_management.mapper;

import com.tehoturapp.team_task_management.dto.TaskDto;
import com.tehoturapp.team_task_management.dto.TaskListDto;
import com.tehoturapp.team_task_management.dto.UserDto;
import com.tehoturapp.team_task_management.persistence.entity.Task;
import com.tehoturapp.team_task_management.persistence.entity.TaskList;
import com.tehoturapp.team_task_management.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskListDtoMapperImpl implements DtoMapper<TaskList, TaskListDto> {

    private final DtoMapper<Task, TaskDto> taskDtoMapper;
    private final DtoMapper<User, UserDto> userDtoMapper;

    @Override
    public TaskList toEntity(TaskListDto taskListDto) {
        if (taskListDto == null) {
            return null;
        }
        return new TaskList(
                taskListDto.getId(),
                taskListDto.getTitle(),
                taskListDto.getTasks()
                        .stream()
                        .map(taskDtoMapper::toEntity)
                        .collect(Collectors.toList()),
                taskListDto.getUsers()
                        .stream()
                        .map(userDtoMapper::toEntity)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        if (taskList == null) {
            return null;
        }
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getTasks()
                        .stream()
                        .map(taskDtoMapper::toDto)
                        .collect(Collectors.toList()),
                taskList.getUsers()
                        .stream()
                        .map(userDtoMapper::toDto)
                        .collect(Collectors.toList())
        );
    }
}
