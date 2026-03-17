package com.picpaysimplificado.domain.transaction;

import com.picpaysimplificado.domain.transaction.dto.TransactionRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idAffectedUser;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private LocalDateTime timestamp;

    public Transaction(TransactionRequestDTO transactionRequest) {
        this.idAffectedUser = transactionRequest.idAffectedUser();
        this.amount = transactionRequest.amount();
        this.timestamp = LocalDateTime.now();
    }
}
