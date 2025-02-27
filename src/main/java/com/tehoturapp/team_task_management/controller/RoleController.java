package com.tehoturapp.team_task_management.controller;

import com.tehoturapp.team_task_management.dto.RoleDto;
import com.tehoturapp.team_task_management.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * Gets a list of all roles.
     * @return List of all roles with HTTP status 200 (OK)
     */
    @GetMapping()
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    /**
     * Creates a new role.
     * @param roleDto new role data
     * @return Created role with HTTP status 201 (CREATED)
     */
    @PostMapping()
    public ResponseEntity<RoleDto> createNewRole(@Valid @RequestBody RoleDto roleDto){
        return new ResponseEntity<>(roleService.createNewRole(roleDto), HttpStatus.CREATED);
    }

    /**
     *
     * Updates the role by its ID.
     * @param roleDto role data
     * @param roleId role ID
     * @return Updated role with HTTP status 200 (OK)
     */
    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDto> updateRoleById(@Valid @RequestBody RoleDto roleDto, @PathVariable Integer roleId){
        return new ResponseEntity<>(roleService.updateRoleById(roleDto, roleId), HttpStatus.OK);
    }

    /**
     * Deletes a role by its ID.
     * @param roleId role ID
     * @return HTTP status 204 (NO CONTENT)
     */
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Integer roleId){
        roleService.deleteRoleById(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
