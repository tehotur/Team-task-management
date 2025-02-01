package com.tehoturapp.team_task_management.controller;

import com.tehoturapp.team_task_management.dto.TaskDto;
import com.tehoturapp.team_task_management.service.TaskService;
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

    @PostMapping()
    public ResponseEntity<TaskDto> createNewTask(@PathVariable Integer taskListId, @RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.createNewTask(taskListId, taskDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<TaskDto>> getAllTasksByTaskListId(@PathVariable Integer taskListId){
        return new ResponseEntity<>(taskService.getAllTasksByTaskListId(taskListId), HttpStatus.OK);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDto> patchTask(@RequestBody Map<String, Object> patchUpdates, @PathVariable Integer taskListId, @PathVariable Integer taskId){
        return new ResponseEntity<>(taskService.patchTask(patchUpdates, taskListId, taskId), HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Integer taskId){
        taskService.deleteTaskById(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
