package com.ecommerce.authservice.Service;

import com.ecommerce.authservice.Config.AuthConfig;
import com.ecommerce.authservice.Model.UserCredentials;
import com.ecommerce.authservice.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public String userCreate(UserCredentials userCredentials) {
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        authRepository.save(userCredentials);
        return "User created";
    }
}