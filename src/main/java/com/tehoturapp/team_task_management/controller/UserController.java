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

    /**
     * Creates a new user.
     * @param userDto new user data
     * @return Created user with HTTP status 201 (CREATED)
     */
    @PostMapping()
    public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createNewUser(userDto), HttpStatus.CREATED);
    }

    /**
     * Gets a list of all users.
     * @return List of all users with HTTP status 200 (OK)
     */
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Gets the user by their ID.
     * @param userId user ID
     * @return User with the given ID with HTTP status 200 (OK)
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    /**
     * Updates user by their ID.
     * @param userDto new user data
     * @param userId user ID
     * @return Updated user with HTTP status 200 (OK)
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto userDto, @PathVariable Long userId){
        return new ResponseEntity<>(userService.updateUserById(userDto, userId), HttpStatus.OK);
    }

    /**
     * Deletes user by their ID.
     * @param userId user ID
     * @return HTTP status 204 (NO CONTENT)
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Assigns a role to the user.
     * @param userId user ID
     * @param roleId role ID
     * @return Updated user with HTTP status 201 (CREATED)
     */
    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> assignRoleToUserById(@PathVariable Long userId, @PathVariable Integer roleId){
       return new ResponseEntity<>(userService.assignRoleToUserById(userId, roleId), HttpStatus.CREATED);
    }

    /**
     * Removes a role from the user.
     * @param userId user ID
     * @param roleId role ID
     * @return Updated user with HTTP status 200 (OK)
     */
    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> removeRoleFromUserById(@PathVariable Long userId, @PathVariable Integer roleId){
        return new ResponseEntity<>(userService.removeRoleFromUserById(userId, roleId), HttpStatus.OK);
    }

    /**
     * Assigns a user to a task list.
     * @param userId user ID
     * @param taskListId task list ID
     * @return Updated task list with HTTP status 201 (CREATED)
     */
    @PostMapping("/{userId}/task-lists/{taskListId}")
    public ResponseEntity<TaskListDto> assignUserToTaskListById(@PathVariable Long userId, @PathVariable Integer taskListId){
        return new ResponseEntity<>(userService.assignUserToTaskListById(userId, taskListId), HttpStatus.CREATED);
    }

}
