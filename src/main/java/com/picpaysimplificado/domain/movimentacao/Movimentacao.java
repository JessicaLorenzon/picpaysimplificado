package com.picpaysimplificado.domain.movimentacao;

import com.picpaysimplificado.domain.movimentacao.dto.deposito.DepositoRequestDTO;
import com.picpaysimplificado.domain.movimentacao.dto.saque.SaqueRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuarioAfetado;

    private BigDecimal quantia;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    private LocalDateTime dataMovimentacao;

    public Movimentacao(Long idUsuarioAfetado, BigDecimal quantia, TipoMovimentacao tipoMovimentacao) {
        this.idUsuarioAfetado = idUsuarioAfetado;
        this.quantia = quantia;
        this.tipo = tipoMovimentacao;
        this.dataMovimentacao = LocalDateTime.now();
    }

    public Movimentacao(DepositoRequestDTO body) {
        this.idUsuarioAfetado = body.idDestinatario();
        this.quantia = body.quantia();
    }

    public Movimentacao(SaqueRequestDTO body) {
        this.idUsuarioAfetado = body.idDestinatario();
        this.quantia = body.quantia();
    }

    public Movimentacao(BigDecimal quantia, Long idUsuarioAfetado) {
        this.idUsuarioAfetado = idUsuarioAfetado;
        this.quantia = quantia;
    }
}
