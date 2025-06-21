package com.example.BeU2W3_Project.services;

import com.example.BeU2W3_Project.dto.UserLoginDTO;
import com.example.BeU2W3_Project.dto.UserRegisterDTO;
import com.example.BeU2W3_Project.models.User;
import com.example.BeU2W3_Project.repositories.UserRepository;
import com.example.BeU2W3_Project.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateAndGenerateToken(UserLoginDTO payload) {
        User user = userService.findByUsername(payload.getUsername());
        if (passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedOperationException("Credenziali non valide.");
        }
    }

    public User registerUser(UserRegisterDTO payload) {
        userRepository.findByUsername(payload.getUsername()).ifPresent(user -> {
            throw new UnauthorizedOperationException("Username " + user.getUsername() + " già in uso.");
        });
        User newUser = new User(payload.getUsername(), passwordEncoder.encode(payload.getPassword()), payload.getRole());
        return userRepository.save(newUser);
    }

}
