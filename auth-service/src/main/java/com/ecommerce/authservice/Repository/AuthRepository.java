package com.ecommerce.authservice.Repository;

import com.ecommerce.authservice.Model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserCredentials,Integer> {
}
