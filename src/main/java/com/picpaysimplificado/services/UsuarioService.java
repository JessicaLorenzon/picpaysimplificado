package com.picpaysimplificado.services;

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

    @Transactional
    public Usuario inserir(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void depositar(BigDecimal quantia, Long idDestinatario) {
        Usuario usuario = usuarioRepository.findById(idDestinatario).orElseThrow(() -> new UsuarioNaoEncontradoException(idDestinatario));
        usuario.creditar(quantia);
    }
}
