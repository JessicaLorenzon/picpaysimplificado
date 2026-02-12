package com.picpaysimplificado.exceptions.handler;

import com.picpaysimplificado.exceptions.SaldoInsuficienteException;
import com.picpaysimplificado.exceptions.TransferenciaBloqueadaException;
import com.picpaysimplificado.exceptions.TransferenciaImpedidaException;
import com.picpaysimplificado.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ProblemDetail saldoInsuficienteException(SaldoInsuficienteException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Saldo Insuficiente");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://picpaysimplificado.com/errors/saldo-insuficiente"));

        return problemDetail;
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ProblemDetail usuarioNaoEncontrado(UsuarioNaoEncontradoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Usuário não encontrado");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://picpaysimplificado.com/errors/usuario-nao-encontrado"));

        return problemDetail;
    }

    @ExceptionHandler(TransferenciaImpedidaException.class)
    public ProblemDetail transferenciaImpedida(TransferenciaImpedidaException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Transferencia impedida");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://picpaysimplificado.com/errors/transferencia-impedida"));

        return problemDetail;
    }

    @ExceptionHandler(TransferenciaBloqueadaException.class)
    public ProblemDetail transacaoBloqueada(TransferenciaBloqueadaException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Transferencia bloqueada");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://picpaysimplificado.com/errors/transferencia-bloqueada"));

        return problemDetail;
    }
}
