package com.picpaysimplificado.exceptions;

public class TransferBlockedException extends RuntimeException {

    public TransferBlockedException() {

        super("Users of the shopkeeper type are not authorized to make transfers");
    }
}
