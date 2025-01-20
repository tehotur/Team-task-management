package com.tehoturapp.team_task_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDto {
    private Integer id;
    private String title;
    private List<TaskDto> tasks;
    private List<UserDto> users;
}
