package com.ecommerce.authservice.Repository;

import com.ecommerce.authservice.Model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<UserCredentials,Integer> {
    Optional<UserCredentials> findByName(String username);
}
