package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.dto.UserRequestDTO;
import com.picpaysimplificado.domain.user.dto.UserResponseDTO;
import com.picpaysimplificado.exceptions.UserNotFoundException;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponseDTO save(UserRequestDTO userRequest) {
        User newUser = new User();
        newUser.setFullName(userRequest.fullName());
        newUser.setCPF(userRequest.CPF());
        newUser.setEmail(userRequest.email());
        newUser.setPassword(userRequest.password());
        newUser.setBalance(BigDecimal.ZERO);
        newUser.setUserType(userRequest.userType());

        this.userRepository.save(newUser);

        return new UserResponseDTO(newUser);
    }

    public User findUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
