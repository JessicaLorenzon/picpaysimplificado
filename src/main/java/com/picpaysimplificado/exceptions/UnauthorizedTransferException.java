package com.picpaysimplificado.exceptions;

public class UnauthorizedTransferException extends RuntimeException {
    public UnauthorizedTransferException() {

        super("Unauthorized transfer");
    }
}
