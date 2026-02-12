package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.usuario.TipoUsuario;
import com.picpaysimplificado.domain.usuario.Usuario;
import com.picpaysimplificado.exceptions.UsuarioNaoEncontradoException;
import com.picpaysimplificado.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscaUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario inserir(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Boolean podeTransferir(Usuario usuario) {
        return usuario.getTipo() != TipoUsuario.LOJISTA;
    }

    public Boolean temSaldoSuficiente(Usuario usuario, BigDecimal quantidade) {
        return usuario.getSaldo().compareTo(quantidade) >= 0;
    }

}
