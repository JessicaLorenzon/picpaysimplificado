package com.picpaysimplificado.domain.user;

import com.picpaysimplificado.domain.user.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String CPF;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserRequestDTO userRequest) {
        this.fullName = userRequest.fullName();
        this.CPF = userRequest.CPF();
        this.email = userRequest.email();
        this.password = userRequest.password();
        this.balance = BigDecimal.ZERO;
        this.userType = userRequest.userType();
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public Boolean hasSufficientBalance(User user, BigDecimal amount) {
        return user.getBalance().compareTo(amount) >= 0;
    }
}
