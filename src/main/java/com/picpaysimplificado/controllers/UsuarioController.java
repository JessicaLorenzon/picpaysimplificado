package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.usuario.Usuario;
import com.picpaysimplificado.domain.usuario.dto.RegistroRequestDTO;
import com.picpaysimplificado.domain.usuario.dto.RegistroResponseDTO;
import com.picpaysimplificado.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<RegistroResponseDTO> inserir(@RequestBody @Valid RegistroRequestDTO body) {
        Usuario novoUsario = new Usuario(body);
        RegistroResponseDTO response = new RegistroResponseDTO(usuarioService.inserir(novoUsario));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
