package com.br.starwars.exception;


import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@Setter
public class StandardError {

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(){

    }

    public StandardError(Long timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
