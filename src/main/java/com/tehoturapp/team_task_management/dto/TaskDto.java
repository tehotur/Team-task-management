package com.tehoturapp.team_task_management.dto;


import com.tehoturapp.team_task_management.persistence.entity.TaskList;
import com.tehoturapp.team_task_management.persistence.entity.TaskPriority;
import com.tehoturapp.team_task_management.persistence.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Integer id;
    @NotNull
    private String title;
    private String description;
    private TaskPriority taskPriority;
    private TaskStatus taskStatus;
}
