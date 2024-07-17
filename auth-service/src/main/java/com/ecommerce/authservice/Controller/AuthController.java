package com.ecommerce.authservice.Controller;

import com.ecommerce.authservice.Model.UserCredentials;
import com.ecommerce.authservice.Service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

@Slf4j
public class AuthController {
    @Autowired
     AuthService authService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredentials userCredentials){
        log.debug("User credentials: {}", userCredentials.getName());
        log.debug("User password: {}", userCredentials.getPassword());
       return authService.userCreate(userCredentials);


    }

    @GetMapping("/token")
    public String getToken(UserCredentials userCredentials){
        return authService.generateToken(userCredentials.getName());
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        return "token valid";
    }


}
