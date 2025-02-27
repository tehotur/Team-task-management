package com.tehoturapp.team_task_management.controller;

import com.tehoturapp.team_task_management.dto.TaskDto;
import com.tehoturapp.team_task_management.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task-lists/{taskListId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Creates a new task in the given task list.
     * @param taskListId task list ID
     * @param taskDto new task data
     * @return Created task with HTTP status 201 (CREATED)
     */
    @PostMapping()
    public ResponseEntity<TaskDto> createNewTask(@PathVariable Integer taskListId,@Valid @RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.createNewTask(taskListId, taskDto), HttpStatus.CREATED);
    }

    /**
     * Gets a list of all tasks in the given task list.
     * @param taskListId task list ID
     * @return List of all tasks in task list with HTTP status 200 (OK)
     */
    @GetMapping()
    public ResponseEntity<List<TaskDto>> getAllTasksByTaskListId(@PathVariable Integer taskListId){
        return new ResponseEntity<>(taskService.getAllTasksByTaskListId(taskListId), HttpStatus.OK);
    }

    /**
     * Partially updates the task with PATCH.
     * @param patchUpdates changed task data
     * @param taskId task ID
     * @return Updated task with HTTP status 200 (OK)
     */
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDto> patchTask(@RequestBody Map<String, Object> patchUpdates, @PathVariable Integer taskListId, @PathVariable Integer taskId){
        return new ResponseEntity<>(taskService.patchTask(patchUpdates, taskListId, taskId), HttpStatus.OK);
    }

    /**
     * Deletes a task by its ID.
     * @param taskId task ID
     * @return HTTP status 204 (NO CONTENT)
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Integer taskId){
        taskService.deleteTaskById(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
