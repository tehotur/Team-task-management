package com.tehoturapp.team_task_management.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Integer taskId) {
        super("Task with ID " + taskId + "not exist.");
    }
}
