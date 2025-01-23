package com.tehoturapp.team_task_management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDto {
    private Integer id;
    @NotNull
    private String title;
    private List<TaskDto> tasks = new ArrayList<>();
    private List<UserDto> users = new ArrayList<>();
}
