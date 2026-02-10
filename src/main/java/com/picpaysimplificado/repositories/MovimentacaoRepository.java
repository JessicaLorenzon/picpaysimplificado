package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.movimentacao.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
