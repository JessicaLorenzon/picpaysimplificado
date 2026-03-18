package com.picpaysimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    public boolean authorizeTransaction() {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(this.authApiUrl, Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            Map data = (Map) authorizationResponse.getBody().get("data");
            return (Boolean) data.get("authorization");
        } else return false;
    }
}
