package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
