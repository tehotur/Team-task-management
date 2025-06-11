package com.tehoturapp.team_task_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "Name cannot be null or empty.")
    private String name;
    @Email(message = "Not valid email format.")
    @NotBlank(message = "Email cannot be null or empty.")
    private String email;
    @NotBlank(message = "Password cannot be null or empty.")
    @Size(min = 8, message = "Password must have at least 8 characters.")
    private String password;
    private Set<RoleDto> roles;
}
