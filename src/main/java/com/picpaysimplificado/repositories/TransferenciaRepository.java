package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.transferencia.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
