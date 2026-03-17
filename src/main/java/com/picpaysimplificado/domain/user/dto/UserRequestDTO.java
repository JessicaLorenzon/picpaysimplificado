package com.picpaysimplificado.domain.user.dto;

import com.picpaysimplificado.domain.user.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotBlank
        String fullName,
        @NotBlank
        String CPF,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        UserType userType) {
}
