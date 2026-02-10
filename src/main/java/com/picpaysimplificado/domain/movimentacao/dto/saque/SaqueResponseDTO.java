package com.picpaysimplificado.domain.movimentacao.dto.saque;

import com.picpaysimplificado.domain.movimentacao.Movimentacao;
import com.picpaysimplificado.domain.movimentacao.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SaqueResponseDTO(
        Long id,
        BigDecimal quantia,
        Long idDestinatario,
        TipoMovimentacao tipoMovimentacao,
        LocalDateTime dataMovimentacao) {

    public SaqueResponseDTO(Movimentacao movimentacao) {
        this(movimentacao.getId(), movimentacao.getQuantia(), movimentacao.getIdUsuarioAfetado(), movimentacao.getTipo(), movimentacao.getDataMovimentacao());
    }
}
