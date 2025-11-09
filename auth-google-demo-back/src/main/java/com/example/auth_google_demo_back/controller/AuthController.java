package com.example.auth_google_demo_back.controller;

import com.example.auth_google_demo_back.jwt.JwtService;
import com.example.auth_google_demo_back.model.GoogleTokenResponse;
import com.example.auth_google_demo_back.model.User;
import com.example.auth_google_demo_back.service.GoogleService;
import com.example.auth_google_demo_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleService googleService;
    private final UserService userService;
    private final JwtService jwtService;


    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String,String> body) {
        try {
            String code = body.get("code");

            // 1️⃣ Intercambiar code por token en Google
            GoogleTokenResponse tokenResponse = googleService.exchangeCodeForToken(code);

            // 2️⃣ Obtener información del usuario
            Map<String,Object> userInfo = googleService.getUserInfo(tokenResponse.getAccess_token());

            // 3️⃣ Registrar/actualizar en MySQL
            User user = userService.registerOrUpdate(userInfo);

            // 4️⃣ Generar JWT propio
            String jwt = jwtService.generateToken(user);

            return ResponseEntity.ok(Map.of(
                    "token", jwt,
                    "email", user.getEmail(),
                    "name", user.getName(),
                    "picture", user.getPicture()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @GetMapping("/google/login-url")
    public Map<String, String> getLoginUrl() {
        String scope = "openid email profile";
        String responseType = "code";

        String url = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=" + responseType
                + "&scope=" + scope;

        return Map.of("url", url);
    }
}
