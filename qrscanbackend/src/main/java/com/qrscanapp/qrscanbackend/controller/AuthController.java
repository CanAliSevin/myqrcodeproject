package com.qrscanapp.qrscanbackend.controller;

import com.qrscanapp.qrscanbackend.dto.LoginRequest;
import com.qrscanapp.qrscanbackend.dto.LoginResponse;
import com.qrscanapp.qrscanbackend.dto.RegisterRequest;
import com.qrscanapp.qrscanbackend.model.User;
import com.qrscanapp.qrscanbackend.service.AuthService;
import com.qrscanapp.qrscanbackend.service.JwtService; // JwtService'i import et
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager; // AuthenticationManager'ı enjekte et
    private final JwtService jwtService; // JwtService'i enjekte et

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;//Spring Security’nin kullanıcı doğrulayıcısı.
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User registeredUser = authService.registerUser(request);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            // Spring Security'nin kimlik doğrulamasını tetikle
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Kimlik doğrulama başarılı olduysa
            if (authentication.isAuthenticated()) {
                // Kullanıcı detaylarını al
                User userDetails = (User) authentication.getPrincipal();

                // Kullanıcı için JWT token oluşturur burası
                String jwtToken = jwtService.generateToken(userDetails);

                // LoginResponse objesini oluştur ve tokenı ekle
                LoginResponse response = new LoginResponse(
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getCompany(),
                        userDetails.getFirstname(),
                        userDetails.getLastname(),
                        jwtToken // <<< Tokenı ekle
                );
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // veya özel bir hata mesajı
            }
        } catch (Exception e) {
            // Kimlik doğrulama hatası (örn: yanlış şifre)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // veya özel bir hata mesajı
        }
    }
}
