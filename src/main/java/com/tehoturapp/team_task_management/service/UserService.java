package com.tehoturapp.team_task_management.service;

import com.tehoturapp.team_task_management.dto.UserDto;
import com.tehoturapp.team_task_management.exception.IdsNotMatchException;
import com.tehoturapp.team_task_management.exception.RoleNotFoundException;
import com.tehoturapp.team_task_management.exception.UserNotFoundException;
import com.tehoturapp.team_task_management.mapper.DtoMapper;
import com.tehoturapp.team_task_management.persistence.dao.RoleRepository;
import com.tehoturapp.team_task_management.persistence.dao.UserRepository;
import com.tehoturapp.team_task_management.persistence.entity.Role;
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
    private final DtoMapper<User, UserDto> userDtoMapper;

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

    public UserDto getUserById(Long userId) {
        User findedUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return userDtoMapper.toDto(findedUser);
    }
    @Transactional
    public UserDto updateUserById(UserDto userDto, Long userId) {
        if (!userId.equals(userDto.getId())){
            throw new IdsNotMatchException();
        }
        User userFromDb = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userFromDb.setName(userDto.getName());
        userFromDb.setEmail(userDto.getEmail());
        userFromDb.setPassword(userDto.getPassword());

        userRepository.save(userFromDb);
        return userDtoMapper.toDto(userFromDb);
    }
    @Transactional
    public UserDto assignRoleToUserById(Long userId, Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(RoleNotFoundException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.setRole(role);
        userRepository.save(user);

        return userDtoMapper.toDto(user);
    }
    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
