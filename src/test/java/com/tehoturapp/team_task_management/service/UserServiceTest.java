package com.tehoturapp.team_task_management.service;

import com.tehoturapp.team_task_management.dto.RoleDto;
import com.tehoturapp.team_task_management.dto.UserDto;
import com.tehoturapp.team_task_management.exception.RoleNotFoundException;
import com.tehoturapp.team_task_management.exception.UserNotFoundException;
import com.tehoturapp.team_task_management.mapper.DtoMapper;
import com.tehoturapp.team_task_management.persistence.dao.RoleRepository;
import com.tehoturapp.team_task_management.persistence.dao.UserRepository;
import com.tehoturapp.team_task_management.persistence.entity.Role;
import com.tehoturapp.team_task_management.persistence.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DtoMapper<User, UserDto> userDtoMapper;
    @InjectMocks
    private UserService userService;

    private Role role;
    private User user;
    private RoleDto roleDto;
    private UserDto expectedUserDto;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setName("user1");
        user.setEmail("user@email.com");
        user.setPassword("password1234");
        user.setRoles(new HashSet<>());

        role = new Role();
        role.setId(1);
        role.setName("admin");

        roleDto = new RoleDto(role.getId(), role.getName());
        expectedUserDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), Set.of(roleDto));
    }

    @Test
    void shouldAssignRoleToUserWhenUserAndRoleExist(){

        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userDtoMapper.toDto(user)).thenReturn(expectedUserDto);

        UserDto result = userService.assignRoleToUserById(user.getId(), role.getId());

        assertEquals(expectedUserDto, result);
        assertTrue(user.getRoles().contains(role));

        verify(userRepository).save(user);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserNotExist(){
        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class,
                () -> userService.assignRoleToUserById(user.getId(), role.getId()));

        assertEquals("User with ID " + user.getId() + " not found", userNotFoundException.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowRoleNotFoundExceptionWhenRoleNotExist(){
        when(roleRepository.findById(role.getId())).thenReturn(Optional.empty());

        RoleNotFoundException roleNotFoundException = assertThrows(RoleNotFoundException.class,
                () -> userService.assignRoleToUserById(user.getId(), role.getId()));

        assertEquals("Role with ID " + role.getId() + " not exist", roleNotFoundException.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }

}
