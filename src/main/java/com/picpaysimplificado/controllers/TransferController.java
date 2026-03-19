package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transfer.dto.TransferRequestDTO;
import com.picpaysimplificado.domain.transfer.dto.TransferResponseDTO;
import com.picpaysimplificado.services.TransferService;
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
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferResponseDTO> transfer(@RequestBody @Valid TransferRequestDTO transferRequest) {
        TransferResponseDTO newTransfer = this.transferService.transfer(transferRequest);

        return new ResponseEntity<>(newTransfer, HttpStatus.OK);
    }

}
