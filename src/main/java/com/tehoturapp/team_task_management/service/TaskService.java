package com.tehoturapp.team_task_management.service;

import com.tehoturapp.team_task_management.dto.TaskDto;
import com.tehoturapp.team_task_management.exception.TaskListNotFoundException;
import com.tehoturapp.team_task_management.exception.TaskNotFoundException;
import com.tehoturapp.team_task_management.exception.TaskNotInTaskListException;
import com.tehoturapp.team_task_management.mapper.DtoMapper;
import com.tehoturapp.team_task_management.persistence.dao.TaskListRepository;
import com.tehoturapp.team_task_management.persistence.dao.TaskRepository;
import com.tehoturapp.team_task_management.persistence.entity.Task;
import com.tehoturapp.team_task_management.persistence.entity.TaskList;
import com.tehoturapp.team_task_management.persistence.entity.TaskPriority;
import com.tehoturapp.team_task_management.persistence.entity.TaskStatus;
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

    /**
     * Helper method for finding task list by its ID.
     * @param taskListId task list ID
     * @return Founded task list
     * @throws TaskListNotFoundException if task list with given ID does not exist
     */
    private TaskList findTaskListById(Integer taskListId){
        return taskListRepository.findById(taskListId)
                .orElseThrow(() -> new TaskListNotFoundException(taskListId));
    }

    /**
     * Creates new task within the specified task list.
     * @param taskListId task list ID
     * @param taskDto data of new task
     * @return Created task in form of Dto
     */
    @Transactional
    public TaskDto createNewTask(Integer taskListId, TaskDto taskDto) {

        TaskList taskList = findTaskListById(taskListId);

        Task taskEntity = taskMapper.toEntity(taskDto);
        // Binding task to task list
        taskEntity.setTaskList(taskList);

        Task savedTask = taskRepository.save(taskEntity);

        return taskMapper.toDto(savedTask);
    }

    /**
     * Gets all tasks within the given task list.
     * @param taskListId task list ID
     * @return List of tasks in Dto form
     */
    public List<TaskDto> getAllTasksByTaskListId(Integer taskListId) {

        TaskList taskList = findTaskListById(taskListId);

        List<Task> tasks = taskList.getTasks().stream().toList();

        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    /**
     * Patches an existing task according to the specified changes.
     * @param patchUpdates map containing data for task update
     * @param taskListId task list ID for validation
     * @param taskId task ID
     * @return Updated task in form of Dto
     * @throws TaskNotFoundException if task not exist
     * @throws TaskNotInTaskListException if task does not belong to a specific task list
     */
    @Transactional
    public TaskDto patchTask(Map<String, Object> patchUpdates, Integer taskListId, Integer taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        if (!task.getTaskList().getId().equals(taskListId)){
            throw new TaskNotInTaskListException(taskListId, taskId);
        }

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

    /**
     * Deletes task by its ID.
     * @param taskId task ID
     */
    @Transactional
    public void deleteTaskById(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
