package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transferencia.Transferencia;
import com.picpaysimplificado.domain.usuario.TipoUsuario;
import com.picpaysimplificado.domain.usuario.Usuario;
import com.picpaysimplificado.repositories.TransferenciaRepository;
import com.picpaysimplificado.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransferenciaService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Transactional
    public Transferencia transferir(Transferencia transferencia) {
        Usuario remetente = buscaUsuario(transferencia.getIdRemetente());
        Usuario destinatario = buscaUsuario(transferencia.getIdDestinatario());

        if (remetente.getTipo() == TipoUsuario.LOJISTA) {
            throw new RuntimeException("Lojista não pode fazer transferancia");
        }

        if (remetente.getSaldo().compareTo(transferencia.getQuantia()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        remetente.debitar(transferencia.getQuantia());
        destinatario.creditar(transferencia.getQuantia());

        Transferencia novaTransferencia = new Transferencia(transferencia.getQuantia(), transferencia.getIdRemetente(), transferencia.getIdDestinatario(), LocalDateTime.now());

        return transferenciaRepository.save(novaTransferencia);
    }

    private Usuario buscaUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }
}
