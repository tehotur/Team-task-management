package com.tehoturapp.team_task_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(){
        super("Role not found!");
    }
}
