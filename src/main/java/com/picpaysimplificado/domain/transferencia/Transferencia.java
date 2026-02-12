package com.picpaysimplificado.domain.transferencia;

import com.picpaysimplificado.domain.transferencia.dto.TransferenciaRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal quantia;
    private Long idRemetente;
    private Long idDestinatario;
    private LocalDateTime dataTransferencia;

    public Transferencia(BigDecimal quantia, Long idRemetente, Long idDestinatario, LocalDateTime dataTransferencia) {
        this.quantia = quantia;
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.dataTransferencia = dataTransferencia;
    }

    public Transferencia(TransferenciaRequestDTO body) {
        this.quantia = body.quantia();
        this.idRemetente = body.idRemetente();
        this.idDestinatario = body.idDestinatario();
    }
}
