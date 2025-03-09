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

    /**
     * Creates new task list.
     * @param taskListDto data of new task list
     * @return Created task list in form of Dto
     */
    public TaskListDto createNewTaskList(TaskListDto taskListDto) {
        TaskList taskListEntity = taskListDtoMapper.toEntity(taskListDto);
        TaskList savedTaskListEntity = taskListRepository.save(taskListEntity);
        return taskListDtoMapper.toDto(savedTaskListEntity);
    }

    /**
     * Gets list of all task lists and converts them to DTOs.
     * @return List of all task lists in form of TaskListDto
     */
    public List<TaskListDto> getAllTaskLists() {
        return taskListRepository.findAll()
                .stream()
                .map(taskListDtoMapper::toDto)
                .toList();
    }

    /**
     * Deletes task list by its ID.
     * @param taskListId task list ID
     */
    public void deleteTaskListById(Integer taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
