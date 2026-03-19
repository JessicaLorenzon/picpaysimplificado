package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.transaction.TransactionType;
import com.picpaysimplificado.domain.transaction.dto.TransactionRequestDTO;
import com.picpaysimplificado.domain.transaction.dto.TransactionResponseDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.exceptions.InsufficientFundsException;
import com.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public TransactionResponseDTO transaction(TransactionRequestDTO transactionRequest, TransactionType type) {
        User user = this.userService.findUserById(transactionRequest.affectedUserId());
        BigDecimal amount = transactionRequest.amount();

        if (type == TransactionType.DEPOSIT) user.deposit(amount);

        if (type == TransactionType.WITHDRAW) {
            if (!user.hasSufficientBalance(user, amount)) throw new InsufficientFundsException();
            user.withdraw(amount);
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAffectedUserId(user.getId());
        newTransaction.setAmount(amount);
        newTransaction.setTransactionType(type);
        newTransaction.setTimestamp(LocalDateTime.now());

        this.transactionRepository.save(newTransaction);

        return new TransactionResponseDTO(newTransaction);
    }
}
