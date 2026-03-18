package com.picpaysimplificado.domain.transfer.dto;

import com.picpaysimplificado.domain.transfer.Transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferResponseDTO(
        Long id,
        BigDecimal amount,
        Long senderId,
        Long recieverId,
        LocalDateTime timestamp) {

    public TransferResponseDTO(Transfer newTransfer) {
        this(newTransfer.getId(), newTransfer.getAmount(), newTransfer.getSender().getId(), newTransfer.getReciever().getId(), newTransfer.getTimestamp());
    }
}
