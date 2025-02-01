package com.tehoturapp.team_task_management.controller;

import com.tehoturapp.team_task_management.dto.TaskListDto;
import com.tehoturapp.team_task_management.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;

    @PostMapping()
    public ResponseEntity<TaskListDto> createNewTaskList(@RequestBody TaskListDto taskListDto){
        return new ResponseEntity<>(taskListService.createNewTaskList(taskListDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<TaskListDto>> getAllTaskLists(){
        return new ResponseEntity<>(taskListService.getAllTaskLists(), HttpStatus.OK);
    }

    @DeleteMapping("/{taskListId}")
    public ResponseEntity<Void> deleteTaskListById(@PathVariable Integer taskListId){
        taskListService.deleteTaskListById(taskListId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
