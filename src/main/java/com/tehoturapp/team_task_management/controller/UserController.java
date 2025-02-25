package com.tehoturapp.team_task_management.controller;

import com.tehoturapp.team_task_management.dto.TaskListDto;
import com.tehoturapp.team_task_management.dto.UserDto;
import com.tehoturapp.team_task_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createNewUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto userDto, @PathVariable Long userId){
        return new ResponseEntity<>(userService.updateUserById(userDto, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> assignRoleToUserById(@PathVariable Long userId, @PathVariable Integer roleId){
       return new ResponseEntity<>(userService.assignRoleToUserById(userId, roleId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> removeRoleFromUserById(@PathVariable Long userId, @PathVariable Integer roleId){
        return new ResponseEntity<>(userService.removeRoleFromUserById(userId, roleId), HttpStatus.OK);
    }


    @PostMapping("/{userId}/task-lists/{taskListId}")
    public ResponseEntity<TaskListDto> assignUserToTaskListById(@PathVariable Long userId, @PathVariable Integer taskListId){
        return new ResponseEntity<>(userService.assignUserToTaskListById(userId, taskListId), HttpStatus.CREATED);
    }

}
