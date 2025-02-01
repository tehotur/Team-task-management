package com.tehoturapp.team_task_management.service;

import com.tehoturapp.team_task_management.dto.TaskDto;
import com.tehoturapp.team_task_management.mapper.DtoMapper;
import com.tehoturapp.team_task_management.persistence.dao.TaskListRepository;
import com.tehoturapp.team_task_management.persistence.dao.TaskRepository;
import com.tehoturapp.team_task_management.persistence.entity.Task;
import com.tehoturapp.team_task_management.persistence.entity.TaskList;
import com.tehoturapp.team_task_management.persistence.entity.TaskPriority;
import com.tehoturapp.team_task_management.persistence.entity.TaskStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final DtoMapper<Task, TaskDto> taskMapper;

    private TaskList findTaskListById(Integer taskListId){
        return taskListRepository.findById(taskListId)
                .orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public TaskDto createNewTask(Integer taskListId, TaskDto taskDto) {

        TaskList taskList = findTaskListById(taskListId);

        Task taskEntity = taskMapper.toEntity(taskDto);
        taskEntity.setTaskList(taskList);

        Task savedTask = taskRepository.save(taskEntity);

        return taskMapper.toDto(savedTask);
    }

    public List<TaskDto> getAllTasksByTaskListId(Integer taskListId) {
        TaskList taskList = findTaskListById(taskListId);
        List<Task> tasks = taskList.getTasks().stream().toList();
        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }
    @Transactional
    public TaskDto patchTask(Map<String, Object> patchUpdates, Integer taskListId, Integer taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(EntityNotFoundException::new);

        if (patchUpdates.containsKey("title")){
            task.setTitle((String) patchUpdates.get("title"));
        }
        if (patchUpdates.containsKey("description")){
            task.setDescription((String) patchUpdates.get("description"));
        }
        if (patchUpdates.containsKey("taskPriority")){
            task.setTaskPriority(TaskPriority.valueOf((String) patchUpdates.get("taskPriority")));
        }
        if (patchUpdates.containsKey("taskStatus")){
            task.setTaskStatus(TaskStatus.valueOf((String) patchUpdates.get("taskStatus")));
        }

        taskRepository.save(task);

        return taskMapper.toDto(task);
    }
    @Transactional
    public void deleteTaskById(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
