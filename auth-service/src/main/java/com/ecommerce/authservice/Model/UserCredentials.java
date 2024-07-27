package com.ecommerce.authservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Username must not be null")
    @Size(min = 3, max = 20, message = "Username must be of 3 to 20 char")
    private String name;
    @NotNull(message = "Username must not be null")
    @Email(message = "enter valid mail")
    private String email;
    private String password;
}