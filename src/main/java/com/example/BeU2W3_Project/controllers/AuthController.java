package com.example.BeU2W3_Project.controllers;

import com.example.BeU2W3_Project.dto.LoginResponseDTO;
import com.example.BeU2W3_Project.dto.UserLoginDTO;
import com.example.BeU2W3_Project.dto.UserRegisterDTO;
import com.example.BeU2W3_Project.models.User;
import com.example.BeU2W3_Project.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserLoginDTO payload) {
        String token = authService.authenticateAndGenerateToken(payload);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Valid UserRegisterDTO payload) {
        return authService.registerUser(payload);
    }
}
