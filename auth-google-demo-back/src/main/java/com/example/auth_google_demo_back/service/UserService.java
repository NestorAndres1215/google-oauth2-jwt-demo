package com.example.auth_google_demo_back.service;

import com.example.auth_google_demo_back.model.User;
import com.example.auth_google_demo_back.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User registerOrUpdate(Map<String, Object> userInfo) {
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String picture = (String) userInfo.get("picture");

        return userRepo.findByEmail(email).map(u -> {
            // Actualiza los campos si el usuario ya existe
            u.setName(name);
            u.setPicture(picture);
            return userRepo.save(u);
        }).orElseGet(() -> {
            // Crea un nuevo usuario
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setPicture(picture);
            newUser.setProvider("google");
            return userRepo.save(newUser);
        });
    }
}