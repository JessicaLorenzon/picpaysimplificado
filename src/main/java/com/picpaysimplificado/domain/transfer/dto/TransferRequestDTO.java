package com.picpaysimplificado.domain.transfer.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequestDTO(
        @NotNull
        BigDecimal amount,
        @NotNull
        Long senderId,
        @NotNull
        Long recieverId) {
}
