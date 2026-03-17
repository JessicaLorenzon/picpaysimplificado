package com.picpaysimplificado.domain.user.dto;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserResponseDTO(
        Long id,
        String fullName,
        String CPF,
        String email,
        BigDecimal balance,
        UserType userType) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getFullName(), user.getCPF(), user.getEmail(), user.getBalance(), user.getUserType());
    }
}
