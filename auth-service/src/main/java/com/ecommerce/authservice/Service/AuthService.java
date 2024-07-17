package com.ecommerce.authservice.Service;

import com.ecommerce.authservice.Config.AuthConfig;
import com.ecommerce.authservice.Model.UserCredentials;
import com.ecommerce.authservice.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String userCreate(UserCredentials userCredentials) {
        if(userCredentials.getPassword() == null){
            throw new IllegalArgumentException("Password cannot be null");
        }
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        authRepository.save(userCredentials);
        return "User created";
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
