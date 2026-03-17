package com.picpaysimplificado.domain.transaction.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        @NotNull
        Long idAffectedUser,
        @NotNull
        BigDecimal amount) {
}
