package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.usuario.Usuario;
import com.picpaysimplificado.domain.usuario.dto.RegistroRequestDTO;
import com.picpaysimplificado.domain.usuario.dto.RegistroResponseDTO;
import com.picpaysimplificado.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<RegistroResponseDTO>> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodos();

        List<RegistroResponseDTO> response = usuarios.stream().map(x -> new RegistroResponseDTO(x)).toList();

        return ResponseEntity.ok(response);
    }
}
