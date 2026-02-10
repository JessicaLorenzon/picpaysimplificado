package com.picpaysimplificado.domain.usuario.dto;

import com.picpaysimplificado.domain.usuario.TipoUsuario;
import com.picpaysimplificado.domain.usuario.Usuario;

import java.math.BigDecimal;

public record RegistroResponseDTO(
        Long id,
        String nomeCompleto,
        String cpf,
        String email,
        String senha,
        TipoUsuario tipo,
        BigDecimal saldo) {

    public RegistroResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNomeCompleto(), usuario.getCpf(), usuario.getEmail(), usuario.getSenha(), usuario.getTipo(), usuario.getSaldo());
    }
}
