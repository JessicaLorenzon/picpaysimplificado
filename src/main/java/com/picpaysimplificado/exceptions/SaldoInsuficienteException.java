package com.picpaysimplificado.exceptions;

public class SaldoInsuficienteException extends RuntimeException{

    public  SaldoInsuficienteException(){
        super("Conta com saldo insuficiente");
    }
}
