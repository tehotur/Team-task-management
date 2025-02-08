package com.tehoturapp.team_task_management.exception;

public class TaskListNotFoundException extends RuntimeException{
    public TaskListNotFoundException(Integer taskListId){
        super("Task List with ID " + taskListId + " not found");
    }
}
