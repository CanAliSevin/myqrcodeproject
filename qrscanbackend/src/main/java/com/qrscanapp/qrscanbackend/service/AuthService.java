// src/main/java/com/qrscanapp/qrscanbackend/service/AuthService.java (Güncel)
package com.qrscanapp.qrscanbackend.service;

import com.qrscanapp.qrscanbackend.dto.LoginRequest; // Artık loginUser kullanılmadığı için bu import gereksiz olabilir
import com.qrscanapp.qrscanbackend.dto.RegisterRequest;
import com.qrscanapp.qrscanbackend.model.User;
import com.qrscanapp.qrscanbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public User registerUser(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Bu kullanıcı adı zaten kullanılıyor.");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setCompany(request.getCompany());
        newUser.setFirstname(request.getFirstname());
        newUser.setLastname(request.getLastname());

        return userRepository.save(newUser);
    }
}
