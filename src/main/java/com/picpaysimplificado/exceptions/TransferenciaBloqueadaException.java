package com.picpaysimplificado.exceptions;

public class TransferenciaBloqueadaException extends RuntimeException{

    public TransferenciaBloqueadaException(){
        super("Transferencia não foi autorizada");
    }
}
