package com.general.mais_um_springboot.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
