package com.tehoturapp.team_task_management.exception;

public class TaskNotInTaskListException extends RuntimeException {

    public TaskNotInTaskListException(Integer taskListId, Integer taskId){
        super("Task with ID " + taskId + " is not in TaskList with ID " + taskListId);
    }
}
