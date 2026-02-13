package com.example.auth_google_demo_back.service;

import com.example.auth_google_demo_back.model.GoogleTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.http.*;

import java.util.Map;

@Service
public class GoogleService {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    private final RestTemplate restTemplate = new RestTemplate();


    public GoogleService(
            @Value("${spring.security.oauth2.client.registration.google.client-id}") String clientId,
            @Value("${spring.security.oauth2.client.registration.google.client-secret}") String clientSecret,
            @Value("${spring.security.oauth2.client.registration.google.redirect-uri}") String redirectUri
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    public GoogleTokenResponse exchangeCodeForToken(String code) {
        String tokenUrl = "https://oauth2.googleapis.com/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<GoogleTokenResponse> response = restTemplate.postForEntity(
                tokenUrl,
                request,
                GoogleTokenResponse.class
        );

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("Error intercambiando code por token en Google");
        }

        return response.getBody();
    }


    public Map<String, Object> getUserInfo(String accessToken) {
        String userInfoUrl = "https://openidconnect.googleapis.com/v1/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                userInfoUrl,
                HttpMethod.GET,
                entity,
                Map.class
        );


        return response.getBody();
    }
}
