package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.transaction.TransactionType;
import com.picpaysimplificado.domain.transaction.dto.TransactionRequestDTO;
import com.picpaysimplificado.domain.transaction.dto.TransactionResponseDTO;
import com.picpaysimplificado.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponseDTO> deposit(@RequestBody @Valid TransactionRequestDTO transactionRequest) {
        Transaction newTransaction = new Transaction(transactionRequest);
        newTransaction.setTransactionType(TransactionType.DEPOSIT);
        TransactionResponseDTO response = new TransactionResponseDTO(transactionService.transaction(newTransaction, TransactionType.DEPOSIT));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseDTO> withdraw(@RequestBody @Valid TransactionRequestDTO transactionRequest) {
        Transaction newTransaction = new Transaction(transactionRequest);
        newTransaction.setTransactionType(TransactionType.WITHDRAW);
        TransactionResponseDTO response = new TransactionResponseDTO(transactionService.transaction(newTransaction, TransactionType.WITHDRAW));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
