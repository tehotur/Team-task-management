package com.tehoturapp.team_task_management.service;

import com.tehoturapp.team_task_management.dto.RoleDto;
import com.tehoturapp.team_task_management.exception.RoleNotFoundException;
import com.tehoturapp.team_task_management.exception.UserNotFoundException;
import com.tehoturapp.team_task_management.mapper.DtoMapper;
import com.tehoturapp.team_task_management.persistence.dao.RoleRepository;
import com.tehoturapp.team_task_management.persistence.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final DtoMapper<Role, RoleDto> roleDtoMapper;

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleDtoMapper::toDto)
                .toList();
    }

    public RoleDto createNewRole(RoleDto roleDto) {
        Role roleEntity = roleDtoMapper.toEntity(roleDto);
        Role savedRoleEntity = roleRepository.save(roleEntity);
        return roleDtoMapper.toDto(savedRoleEntity);
    }
    @Transactional
    public RoleDto updateRoleById(RoleDto roleDto, Integer roleId) {

        Role roleFromDb = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));

        roleFromDb.setName(roleDto.getName());
        roleRepository.save(roleFromDb);
        return roleDtoMapper.toDto(roleFromDb);
    }
    @Transactional
    public void deleteRoleById(Integer roleId) {
        roleRepository.deleteById(roleId);
    }
}

