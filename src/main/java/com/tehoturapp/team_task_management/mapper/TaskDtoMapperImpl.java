package com.tehoturapp.team_task_management.mapper;

import com.tehoturapp.team_task_management.dto.TaskDto;
import com.tehoturapp.team_task_management.dto.TaskListDto;
import com.tehoturapp.team_task_management.persistence.entity.Task;
import com.tehoturapp.team_task_management.persistence.entity.TaskList;
import com.tehoturapp.team_task_management.persistence.entity.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class TaskDtoMapperImpl implements DtoMapper<Task, TaskDto> {

    @Override
    public Task toEntity(TaskDto taskDto) {
        if (taskDto == null){
            return null;
        }
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getTaskPriority(),
                Optional.ofNullable(taskDto.getTaskStatus()).orElse(TaskStatus.NOT_STARTED),
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        if (task == null){
            return null;
        }
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getTaskPriority(),
                task.getTaskStatus()
        );
    }
}