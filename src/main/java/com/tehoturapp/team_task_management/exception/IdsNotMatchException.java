package com.tehoturapp.team_task_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IdsNotMatchException extends RuntimeException{
    public IdsNotMatchException(){
        super("Ids does not match");
    }
}

