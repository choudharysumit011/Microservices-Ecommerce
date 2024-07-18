package com.ecommerce.authservice.Service;

import com.ecommerce.authservice.DTO.CustomUserDetails;
import com.ecommerce.authservice.Model.UserCredentials;
import com.ecommerce.authservice.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> userDetails = authRepository.findByName(username);
        return userDetails.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
