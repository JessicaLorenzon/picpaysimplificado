package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.transfer.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
