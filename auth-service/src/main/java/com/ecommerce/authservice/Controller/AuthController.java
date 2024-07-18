package com.ecommerce.authservice.Controller;

import com.ecommerce.authservice.Config.InvalidTokenException;
import com.ecommerce.authservice.DTO.UserRequest;
import com.ecommerce.authservice.Model.UserCredentials;
import com.ecommerce.authservice.Service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

@Slf4j
public class AuthController {
    @Autowired
     AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredentials userCredentials){
        log.debug("User credentials: {}", userCredentials.getName());
        log.debug("User password: {}", userCredentials.getPassword());
       return authService.userCreate(userCredentials);


    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody UserRequest userRequest){
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
       try {
           authentication.isAuthenticated();
           return ResponseEntity.ok().body(authService.generateToken(userRequest.getUsername()));
       } catch (InvalidTokenException e) {
           log.info("Authentication failed");
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username or password");
       }

    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        try {
            authService.validateToken(token);
            return ResponseEntity.ok("token valid");
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid token");
        }
    }


}
