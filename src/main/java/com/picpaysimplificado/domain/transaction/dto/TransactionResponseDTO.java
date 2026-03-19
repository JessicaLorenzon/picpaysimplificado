package com.picpaysimplificado.domain.transaction.dto;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long id,
        Long affectedUserId,
        BigDecimal amount,
        TransactionType transactionType,
        LocalDateTime timestamp) {

    public TransactionResponseDTO(Transaction transaction) {
        this(transaction.getId(), transaction.getAffectedUserId(), transaction.getAmount(), transaction.getTransactionType(), transaction.getTimestamp());
    }
}
