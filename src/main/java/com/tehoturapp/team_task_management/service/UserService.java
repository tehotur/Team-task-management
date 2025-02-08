package com.tehoturapp.team_task_management.service;

import com.tehoturapp.team_task_management.dto.TaskListDto;
import com.tehoturapp.team_task_management.dto.UserDto;
import com.tehoturapp.team_task_management.exception.RoleNotFoundException;
import com.tehoturapp.team_task_management.exception.TaskListNotFoundException;
import com.tehoturapp.team_task_management.exception.UserNotFoundException;
import com.tehoturapp.team_task_management.mapper.DtoMapper;
import com.tehoturapp.team_task_management.persistence.dao.RoleRepository;
import com.tehoturapp.team_task_management.persistence.dao.TaskListRepository;
import com.tehoturapp.team_task_management.persistence.dao.UserRepository;
import com.tehoturapp.team_task_management.persistence.entity.Role;
import com.tehoturapp.team_task_management.persistence.entity.TaskList;
import com.tehoturapp.team_task_management.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TaskListRepository taskListRepository;
    private final DtoMapper<User, UserDto> userDtoMapper;
    private final DtoMapper<TaskList, TaskListDto> taskListDtoMapper;

    public User findUserByIdFromDb(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
    public UserDto getUserById(Long userId) {
        User userFromDb = findUserByIdFromDb(userId);
        return userDtoMapper.toDto(userFromDb);
    }

    public UserDto createNewUser(UserDto userDto) {
        User userEntity = userDtoMapper.toEntity(userDto);
        //password encoder... userEntity.setPassword();
        User savedUser = userRepository.save(userEntity);
        return userDtoMapper.toDto(savedUser);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoMapper::toDto)
                .toList();
    }


    @Transactional
    public UserDto updateUserById(UserDto userDto, Long userId) {

        User userFromDb = findUserByIdFromDb(userId);

        userFromDb.setName(userDto.getName());
        userFromDb.setEmail(userDto.getEmail());
        userFromDb.setPassword(userDto.getPassword());

        userRepository.save(userFromDb);
        return userDtoMapper.toDto(userFromDb);
    }

    @Transactional
    public UserDto assignRoleToUserById(Long userId, Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));

        User userFromDb = findUserByIdFromDb(userId);

        userFromDb.setRole(role);
        userRepository.save(userFromDb);

        return userDtoMapper.toDto(userFromDb);
    }
    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public TaskListDto assignUserToTaskListById(Long userId, Integer taskListId) {

        User userFromDb = findUserByIdFromDb(userId);

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new TaskListNotFoundException(taskListId));

        userFromDb.setTaskList(taskList);

        userRepository.save(userFromDb);

        return taskListDtoMapper.toDto(taskList);
    }
}
