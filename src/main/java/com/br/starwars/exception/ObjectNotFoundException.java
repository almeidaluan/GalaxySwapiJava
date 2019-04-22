package com.br.starwars.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4288895116839976448L;

	public ObjectNotFoundException(String message){
        super(message);
    }

}

