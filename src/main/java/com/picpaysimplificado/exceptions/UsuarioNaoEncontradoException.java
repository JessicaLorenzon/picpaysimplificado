package com.picpaysimplificado.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário com id " + id + " não foi encontrado");
    }
}
