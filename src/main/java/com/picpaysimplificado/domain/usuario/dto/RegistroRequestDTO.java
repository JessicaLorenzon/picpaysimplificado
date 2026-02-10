package com.picpaysimplificado.domain.usuario.dto;

import com.picpaysimplificado.domain.usuario.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroRequestDTO(
        @NotBlank
        String nomeCompleto,
        @NotBlank
        String cpf,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotNull
        TipoUsuario tipo) {
}
