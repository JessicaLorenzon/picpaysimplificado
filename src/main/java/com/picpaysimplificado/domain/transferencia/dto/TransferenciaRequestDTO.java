package com.picpaysimplificado.domain.transferencia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferenciaRequestDTO(
        @NotNull
        BigDecimal quantia,
        @NotNull
        Long idRemetente,
        @NotNull
        Long idDestinatario) {
}
