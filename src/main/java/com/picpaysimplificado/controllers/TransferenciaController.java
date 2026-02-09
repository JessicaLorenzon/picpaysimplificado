package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transferencia.Transferencia;
import com.picpaysimplificado.domain.transferencia.dto.TransferenciaRequestDTO;
import com.picpaysimplificado.domain.transferencia.dto.TransferenciaResponseDTO;
import com.picpaysimplificado.services.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<TransferenciaResponseDTO> transferir(@RequestBody @Valid TransferenciaRequestDTO body) {
        Transferencia novaTransferencia = new Transferencia(body);
        TransferenciaResponseDTO response = new TransferenciaResponseDTO(transferenciaService.transferir(novaTransferencia));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
