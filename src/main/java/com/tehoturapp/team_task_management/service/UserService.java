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
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TaskListRepository taskListRepository;
    private final DtoMapper<User, UserDto> userDtoMapper;
    private final DtoMapper<TaskList, TaskListDto> taskListDtoMapper;

    /**
     * Finds a user by its ID.
     * @param userId user ID
     * @return Founded user
     * @throws UserNotFoundException if user not exist
     */
    public User findUserByIdFromDb(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    /**
     * Finds a role by its ID.
     * @param roleId role ID
     * @return Founded role
     * @throws RoleNotFoundException if role not exist
     */
    public Role findRoleByIdFromDb(Integer roleId){
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));
    }

    /**
     * Gets a user by their ID.
     * @param userId user ID
     * @return User in form of Dto
     */
    public UserDto getUserById(Long userId) {
        User userFromDb = findUserByIdFromDb(userId);
        return userDtoMapper.toDto(userFromDb);
    }

    /**
     * Creates a new user.
     * @param userDto data of new user
     * @return Created user in form of Dto
     */
    public UserDto createNewUser(UserDto userDto) {
        User userEntity = userDtoMapper.toEntity(userDto);
        //TO DO password encoder... userEntity.setPassword();
        User savedUser = userRepository.save(userEntity);
        return userDtoMapper.toDto(savedUser);
    }

    /**
     * Gets list of users all users and convert them to DTOs.
     * @return List of all users in form of Dto
     */
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoMapper::toDto)
                .toList();
    }

    /**
     * Updates a user by their ID.
     * @param userDto data of user
     * @param userId user ID
     * @return Updated user in form of Dto
     * @throws UserNotFoundException if user not exist
     */
    @Transactional
    public UserDto updateUserById(UserDto userDto, Long userId) {

        User userFromDb = findUserByIdFromDb(userId);

        userFromDb.setName(userDto.getName());
        userFromDb.setEmail(userDto.getEmail());
        userFromDb.setPassword(userDto.getPassword());

        userRepository.save(userFromDb);

        return userDtoMapper.toDto(userFromDb);
    }

    /**
     * Assigns a role to the user based on their ID.
     * @param userId user ID
     * @param roleId role ID
     * @return User in form of Dto with updated list of roles
     * @throws UserNotFoundException if user not exist
     * @throws RoleNotFoundException if role not exist
     */
    @Transactional
    public UserDto assignRoleToUserById(Long userId, Integer roleId) {

        Role roleFromDb = findRoleByIdFromDb(roleId);
        User userFromDb = findUserByIdFromDb(userId);

        Set<Role> rolesOfUser =  userFromDb.getRoles();
        rolesOfUser.add(roleFromDb);

        userRepository.save(userFromDb);

        return userDtoMapper.toDto(userFromDb);
    }

    /**
     * Removes role from the user by their ID.
     * @param userId user ID
     * @param roleId role ID
     * @return User in form of Dto with updated list of roles
     * @throws UserNotFoundException if user not exist
     * @throws RoleNotFoundException if role not exist
     */
    @Transactional
    public UserDto removeRoleFromUserById(Long userId, Integer roleId) {

        Role roleFromDb = findRoleByIdFromDb(roleId);
        User userFromDb = findUserByIdFromDb(userId);

        Set<Role> rolesOfUser =  userFromDb.getRoles();
        rolesOfUser.remove(roleFromDb);

        userRepository.save(userFromDb);

        return userDtoMapper.toDto(userFromDb);
    }

    /**
     * Deletes user by their ID.
     * @param userId user ID
     */
    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Assigns user to a specific task list.
     * @param userId user ID
     * @param taskListId task list ID
     * @return Task list in form of Dto that was assigned to the user
     * @throws UserNotFoundException if user not exist
     * @throws TaskListNotFoundException if task list not exist
     */
    @Transactional
    public TaskListDto assignUserToTaskListById(Long userId, Integer taskListId) {

        User userFromDb = findUserByIdFromDb(userId);

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new TaskListNotFoundException(taskListId));

        userFromDb.setTaskList(taskList);

        userRepository.save(userFromDb);

        return taskListDtoMapper.toDto(taskList);
    }
}
