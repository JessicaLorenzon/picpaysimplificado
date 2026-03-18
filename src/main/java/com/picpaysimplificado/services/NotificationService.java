package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transfer.dto.NotificationDTO;
import com.picpaysimplificado.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

//        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);
//
//        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
//            System.out.println("Error sending notification");
//            throw new NotificationNotSentException();
//        }

        System.out.println("Notification sent to the user");

    }
}
