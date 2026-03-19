package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transfer.dto.NotificationDTO;
import com.picpaysimplificado.exceptions.NotificationNotSentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    public void sendNotification(Long id, String message) {
        String email = this.userService.findUserById(id).getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        try {
            ResponseEntity<String> response =
                    restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new NotificationNotSentException();
            }
        } catch (Exception ex) {
            throw new NotificationNotSentException();
        }
    }
}
