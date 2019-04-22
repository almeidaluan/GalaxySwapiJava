package com.br.starwars.exception;


import com.br.starwars.component.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static com.br.starwars.component.Messages.*;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> planetNotFound(ObjectNotFoundException planetNotFound, HttpServletRequest httpServletRequest){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(),status.value(), Messages.NOT_FOUND,planetNotFound.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
