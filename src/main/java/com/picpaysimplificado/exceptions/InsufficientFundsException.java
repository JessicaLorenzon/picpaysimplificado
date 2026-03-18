package com.picpaysimplificado.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {

        super("Insufficient funds");
    }
}
