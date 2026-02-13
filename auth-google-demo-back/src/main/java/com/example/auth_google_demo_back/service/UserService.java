package com.example.auth_google_demo_back.service;

import com.example.auth_google_demo_back.model.User;
import com.example.auth_google_demo_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;


    public User registerOrUpdate(Map<String, Object> userInfo) {
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String picture = (String) userInfo.get("picture");

        return userRepo.findByEmail(email).map(u -> {
          
            u.setName(name);
            u.setPicture(picture);
            return userRepo.save(u);
        }).orElseGet(() -> {
    
            User newUser = User.builder()
                    .email(email)
                    .name(name)
                    .picture(picture)
                    .provider("google")
                    .build();
            return userRepo.save(newUser);
        });
    }
}