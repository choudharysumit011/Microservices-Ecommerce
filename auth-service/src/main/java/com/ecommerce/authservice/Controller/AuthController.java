package com.ecommerce.authservice.Controller;

import com.ecommerce.authservice.Model.UserCredentials;
import com.ecommerce.authservice.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String addNewUser(UserCredentials userCredentials){
       return authService.userCreate(userCredentials);


    }

    @GetMapping("/token")
    public String getToken(UserCredentials userCredentials){
        return authService.generateToken(userCredentials.getName());
    }


}
