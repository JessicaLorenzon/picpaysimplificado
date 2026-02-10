package com.picpaysimplificado.domain.usuario;

import com.picpaysimplificado.domain.usuario.dto.RegistroRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    private BigDecimal saldo;


    public Usuario(RegistroRequestDTO body) {
        this.nomeCompleto = body.nomeCompleto();
        this.cpf = body.cpf();
        this.email = body.email();
        this.senha = body.senha();
        this.tipo = body.tipo();
        this.saldo = BigDecimal.ZERO;
    }

    public Boolean podeTransferir() {
        return getTipo() != TipoUsuario.LOJISTA;
    }

    public void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }
}
