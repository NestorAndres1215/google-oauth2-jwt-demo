package com.example.auth_google_demo_back.model;

import jakarta.persistence.*;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    private String picture;

    private String provider; // google, github, etc.

    public User() {
    }

    public User(Long id, String email, String name, String picture, String provider) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.provider = provider;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
