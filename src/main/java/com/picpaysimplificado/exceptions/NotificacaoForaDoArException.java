package com.picpaysimplificado.exceptions;

public class NotificacaoForaDoArException extends RuntimeException {

    public NotificacaoForaDoArException() {

        super("Serviço de notificação está fora do ar");
    }
}
