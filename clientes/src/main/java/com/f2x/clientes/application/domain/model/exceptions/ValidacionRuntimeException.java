package com.f2x.clientes.application.domain.model.exceptions;

public class ValidacionRuntimeException extends RuntimeException {

    public ValidacionRuntimeException(String message){
        super(message);
    }

    public ValidacionRuntimeException(String message, Throwable cause){
        super(message,cause);
    }

    public ValidacionRuntimeException(){
        super();
    }
}
