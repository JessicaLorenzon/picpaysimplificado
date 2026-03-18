package com.picpaysimplificado.exceptions.handler;

import com.picpaysimplificado.exceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ControllerExceptionHandler {

    private ProblemDetail buildProblemDetail(HttpStatus status, String title, String detail, String type) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setType(URI.create(type));

        return problemDetail;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail userNotFoundException(UserNotFoundException e) {
        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                "User not found",
                e.getMessage(),
                "https://picpaysimplificado.com/errors/user-not-found"
        );
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ProblemDetail insufficientFundsException(InsufficientFundsException e) {
        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Insufficient Funds",
                e.getMessage(),
                "https://picpaysimplificado.com/errors/insufficient-funds"
        );
    }

    @ExceptionHandler(TransferBlockedException.class)
    public ProblemDetail transferBlockedException(TransferBlockedException e) {
        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Transfer Blocked",
                e.getMessage(),
                "https://picpaysimplificado.com/errors/transfer-blocked"
        );
    }

    @ExceptionHandler(UnauthorizedTransferException.class)
    public ProblemDetail unauthorizedTransferException(UnauthorizedTransferException e) {
        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Unauthorized Transfer",
                e.getMessage(),
                "https://picpaysimplificado.com/errors/unauthorized-transfer"
        );
    }

    @ExceptionHandler(NotificationNotSentException.class)
    public ProblemDetail notificationNotSentException(NotificationNotSentException e) {
        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Notification not sent",
                e.getMessage(),
                "https://picpaysimplificado.com/errors/notification-not-sent"
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail entradaDuplicada(DataIntegrityViolationException e) {
        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Duplicate entry",
                "User already registered",
                "https://picpaysimplificado.com/errors/duplicate-entry"
        );
    }
}
