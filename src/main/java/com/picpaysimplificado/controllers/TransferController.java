package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transfer.dto.TransferRequestDTO;
import com.picpaysimplificado.domain.transfer.dto.TransferResponseDTO;
import com.picpaysimplificado.services.NotificationService;
import com.picpaysimplificado.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<TransferResponseDTO> transfer(@RequestBody @Valid TransferRequestDTO transferRequest) {
        TransferResponseDTO newTransfer = this.transferService.transfer(transferRequest);

        try {
            this.notificationService.sendNotification(newTransfer.senderId(), "Transfer completed successfully");
            this.notificationService.sendNotification(newTransfer.recieverId(), "Transfer received successfully.");
        } catch (Exception e) {
            System.out.println("Failed to send notification");
        }

        return new ResponseEntity<>(newTransfer, HttpStatus.OK);
    }

}
