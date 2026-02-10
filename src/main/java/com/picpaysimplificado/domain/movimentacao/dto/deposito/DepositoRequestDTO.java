package com.picpaysimplificado.domain.movimentacao.dto.deposito;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositoRequestDTO(
        @NotNull
        BigDecimal quantia,
        @NotNull
        Long idDestinatario) {
}
