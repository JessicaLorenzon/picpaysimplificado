package com.picpaysimplificado.domain.movimentacao.dto.saque;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SaqueRequestDTO(
        @NotNull
        BigDecimal quantia,
        @NotNull
        Long idDestinatario) {
}
