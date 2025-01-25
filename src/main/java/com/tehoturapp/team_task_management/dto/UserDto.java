package com.tehoturapp.team_task_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    private String password;
    private RoleDto role;
}
