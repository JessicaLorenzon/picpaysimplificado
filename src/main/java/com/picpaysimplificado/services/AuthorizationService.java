package com.picpaysimplificado.services;

import com.picpaysimplificado.exceptions.UnauthorizedTransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.authorizationApi}")
    private String authApiUrl;

    public void authorizeTransfer() {
        try {
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(this.authApiUrl, Map.class);

            Map body = authorizationResponse.getBody();
            String status = (String) body.get("status");
            Map data = (Map) body.get("data");
            Boolean authorization = (Boolean) data.get("authorization");

            if (!"success".equals(status) || !authorization.equals(true)) {
                throw new UnauthorizedTransferException();
            }

        } catch (Exception ex) {
            throw new UnauthorizedTransferException();
        }
    }
}
