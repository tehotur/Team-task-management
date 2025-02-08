package com.tehoturapp.team_task_management.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(Integer roleId){
        super("Role with ID " + roleId + " not exist");
    }
}
