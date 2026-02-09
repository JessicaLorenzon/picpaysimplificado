package com.picpaysimplificado.domain.transferencia.dto;

import com.picpaysimplificado.domain.transferencia.Transferencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaResponseDTO(
        Long id,
        BigDecimal quantia,
        Long idRemetente,
        Long idDestinatario,
        LocalDateTime dataTransferencia) {

    public TransferenciaResponseDTO(Transferencia transferencia) {
        this(transferencia.getId(), transferencia.getQuantia(), transferencia.getIdRemetente(), transferencia.getIdDestinatario(), transferencia.getDataTransferencia());
    }
}
