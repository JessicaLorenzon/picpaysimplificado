package com.picpaysimplificado.domain.usuario;

import com.picpaysimplificado.domain.usuario.dto.UsuarioRequestDTO;
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

    public Usuario(String nomeCompleto, String cpf, String email, String senha, TipoUsuario tipo) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.saldo = BigDecimal.ZERO;
    }

    public Usuario(UsuarioRequestDTO body) {
        this.nomeCompleto = body.nomeCompleto();
        this.cpf = body.cpf();
        this.email = body.email();
        this.senha = body.senha();
        this.tipo = body.tipo();
        this.saldo = body.saldo();
    }

    public void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }
}
