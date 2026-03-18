package com.picpaysimplificado.exceptions;

public class NotificationNotSentException extends RuntimeException {
    public NotificationNotSentException() {

        super("The notification service is down");
    }
}
