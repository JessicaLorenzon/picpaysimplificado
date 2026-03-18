package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.transaction.TransactionType;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.exceptions.InsufficientFundsException;
import com.picpaysimplificado.repositories.TransactionRepository;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public Transaction transaction(Transaction transaction, TransactionType type) {
        User user = this.userService.findUserById(transaction.getIdAffectedUser());
        BigDecimal amount = transaction.getAmount();

        if (type == TransactionType.DEPOSIT) user.deposit(amount);

        if (type == TransactionType.WITHDRAW) {
            if (!user.hasSufficientBalance(user,amount)) throw new InsufficientFundsException();
            user.withdraw(amount);
        }

        return this.transactionRepository.save(transaction);
    }
}
