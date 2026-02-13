package com.example.auth_google_demo_back.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String name;
    private String picture;
    private String provider;


}
