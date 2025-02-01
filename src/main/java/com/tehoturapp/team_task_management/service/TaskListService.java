package com.tehoturapp.team_task_management.service;

import com.tehoturapp.team_task_management.dto.TaskListDto;
import com.tehoturapp.team_task_management.mapper.DtoMapper;
import com.tehoturapp.team_task_management.persistence.dao.TaskListRepository;
import com.tehoturapp.team_task_management.persistence.entity.TaskList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final DtoMapper<TaskList, TaskListDto> taskListDtoMapper;

    public TaskListDto createNewTaskList(TaskListDto taskListDto) {
        TaskList taskListEntity = taskListDtoMapper.toEntity(taskListDto);
        TaskList savedTaskListEntity = taskListRepository.save(taskListEntity);
        return taskListDtoMapper.toDto(savedTaskListEntity);
    }

    public List<TaskListDto> getAllTaskLists() {
        return taskListRepository.findAll()
                .stream()
                .map(taskListDtoMapper::toDto)
                .toList();
    }

    public void deleteTaskListById(Integer taskListId) {
        taskListRepository.deleteById(taskListId);
    }


}
