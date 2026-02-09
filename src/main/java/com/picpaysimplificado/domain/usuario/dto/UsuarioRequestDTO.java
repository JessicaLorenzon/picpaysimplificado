package com.picpaysimplificado.domain.usuario.dto;

import com.picpaysimplificado.domain.usuario.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UsuarioRequestDTO(
        @NotBlank
        String nomeCompleto,
        @NotBlank
        String cpf,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotNull
        TipoUsuario tipo,
        BigDecimal saldo) {
}
