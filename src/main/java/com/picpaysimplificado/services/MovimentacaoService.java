package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.movimentacao.Movimentacao;
import com.picpaysimplificado.domain.movimentacao.TipoMovimentacao;
import com.picpaysimplificado.domain.usuario.Usuario;
import com.picpaysimplificado.exceptions.SaldoInsuficienteException;
import com.picpaysimplificado.exceptions.UsuarioNaoEncontradoException;
import com.picpaysimplificado.repositories.MovimentacaoRepository;
import com.picpaysimplificado.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class MovimentacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Movimentacao depositar(Movimentacao movimentacao) {
        Usuario usuario = buscaUsuario(movimentacao.getIdUsuarioAfetado());
        usuario.creditar(movimentacao.getQuantia());

        Movimentacao novaMovimentacao =
                new Movimentacao(movimentacao.getIdUsuarioAfetado(), movimentacao.getQuantia(), TipoMovimentacao.DEPOSITO);

        return movimentacaoRepository.save(novaMovimentacao);
    }

    @Transactional
    public Movimentacao sacar(Movimentacao movimentacao) {
        Usuario usuario = buscaUsuario(movimentacao.getIdUsuarioAfetado());

        if (!(temSaldoSuficiente(usuario, movimentacao.getQuantia()))) throw new SaldoInsuficienteException();

        usuario.debitar(movimentacao.getQuantia());

        Movimentacao novaMovimentacao =
                new Movimentacao(movimentacao.getIdUsuarioAfetado(), movimentacao.getQuantia(), TipoMovimentacao.SAQUE);

        return movimentacaoRepository.save(novaMovimentacao);
    }

    private Usuario buscaUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    private Boolean temSaldoSuficiente(Usuario usuario, BigDecimal quantidade) {
        return usuario.getSaldo().compareTo(quantidade) >= 0;
    }
}
