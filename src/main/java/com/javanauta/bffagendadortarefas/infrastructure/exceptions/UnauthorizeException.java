package com.javanauta.bffagendadortarefas.infrastructure.exceptions;




public class UnauthorizeException extends RuntimeException {

    public UnauthorizeException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }

    public UnauthorizeException(String mensagem){
        super(mensagem);
    }
}
