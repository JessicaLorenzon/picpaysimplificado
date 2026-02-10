package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.movimentacao.Movimentacao;
import com.picpaysimplificado.domain.movimentacao.dto.deposito.DepositoRequestDTO;
import com.picpaysimplificado.domain.movimentacao.dto.deposito.DepositoResponseDTO;
import com.picpaysimplificado.domain.movimentacao.dto.saque.SaqueRequestDTO;
import com.picpaysimplificado.domain.movimentacao.dto.saque.SaqueResponseDTO;
import com.picpaysimplificado.services.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping("/depositar")
    public ResponseEntity<DepositoResponseDTO> depositar(@RequestBody @Valid DepositoRequestDTO body) {
        Movimentacao novaMovimentacao = new Movimentacao(body);
        DepositoResponseDTO response = new DepositoResponseDTO(movimentacaoService.depositar(novaMovimentacao));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sacar")
    public ResponseEntity<SaqueResponseDTO> sacar(@RequestBody @Valid SaqueRequestDTO body) {
        Movimentacao novaMovimentacao = new Movimentacao(body);
        SaqueResponseDTO response = new SaqueResponseDTO(movimentacaoService.sacar(novaMovimentacao));

        return ResponseEntity.ok(response);
    }
}
