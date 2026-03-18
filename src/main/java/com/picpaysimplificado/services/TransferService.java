package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transfer.Transfer;
import com.picpaysimplificado.domain.transfer.dto.TransferRequestDTO;
import com.picpaysimplificado.domain.transfer.dto.TransferResponseDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.exceptions.InsufficientFundsException;
import com.picpaysimplificado.exceptions.TransferBlockedException;
import com.picpaysimplificado.exceptions.UnauthorizedTransferException;
import com.picpaysimplificado.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public TransferResponseDTO transfer(TransferRequestDTO transferRequest) {
        User sender = this.userService.findUserById(transferRequest.senderId());
        User reciever = this.userService.findUserById(transferRequest.recieverId());

        if (!sender.canTransfer()) throw new TransferBlockedException();

        if (!sender.hasSufficientBalance(sender, transferRequest.amount())) throw new InsufficientFundsException();

        if (!this.authorizationService.authorizeTransaction()) throw new UnauthorizedTransferException();

        Transfer newTransfer =  new Transfer();
        newTransfer.setAmount(transferRequest.amount());
        newTransfer.setSender(sender);
        newTransfer.setReciever(reciever);
        newTransfer.setTimestamp(LocalDateTime.now());

        sender.withdraw(transferRequest.amount());
        reciever.deposit(transferRequest.amount());

        this.transferRepository.save(newTransfer);

        this.notificationService.sendNotification(sender, "Transfer completed successfully");
        this.notificationService.sendNotification(reciever, "Transfer received successfully.");

        return new TransferResponseDTO(newTransfer);
    }
}
