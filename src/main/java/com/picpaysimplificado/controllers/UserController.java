package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.dto.UserRequestDTO;
import com.picpaysimplificado.domain.user.dto.UserResponseDTO;
import com.picpaysimplificado.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userRequest) {
        UserResponseDTO newUser = this.userService.save(userRequest);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        List<User> users = this.userService.findAll();
        List<UserResponseDTO> response = users.stream().map(UserResponseDTO::new).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
