package com.tehoturapp.team_task_management.dto;


import com.tehoturapp.team_task_management.persistence.entity.TaskPriority;
import com.tehoturapp.team_task_management.persistence.entity.TaskStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Integer id;
    @NotEmpty(message = "Title of the task cannot be null or empty.")
    private String title;
    private String description;
    private TaskPriority taskPriority;
    private TaskStatus taskStatus;
}
