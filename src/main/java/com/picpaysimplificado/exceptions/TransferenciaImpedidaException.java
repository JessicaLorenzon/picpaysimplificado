package com.picpaysimplificado.exceptions;

public class TransferenciaImpedidaException extends RuntimeException {

    public  TransferenciaImpedidaException() {
        super("Lojista não pode fazer transferancia");
    }
}
