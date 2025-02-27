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

    /**
     * Creates a new task list.
     * @param taskListDto task list data
     * @return Created task list with HTTP status 201 (CREATED)
     */
    @PostMapping()
    public ResponseEntity<TaskListDto> createNewTaskList(@RequestBody TaskListDto taskListDto){
        return new ResponseEntity<>(taskListService.createNewTaskList(taskListDto), HttpStatus.CREATED);
    }

    /**
     * Gets list of all task lists.
     * @return List of all task lists with HTTP status 200 (OK)
     */
    @GetMapping()
    public ResponseEntity<List<TaskListDto>> getAllTaskLists(){
        return new ResponseEntity<>(taskListService.getAllTaskLists(), HttpStatus.OK);
    }

    /**
     * Deletes a task list by its ID.
     * @param taskListId task list ID
     * @return HTTP status 204 (NO CONTENT)
     */
    @DeleteMapping("/{taskListId}")
    public ResponseEntity<Void> deleteTaskListById(@PathVariable Integer taskListId){
        taskListService.deleteTaskListById(taskListId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
