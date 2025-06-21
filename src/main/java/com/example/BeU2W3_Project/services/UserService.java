package com.example.BeU2W3_Project.services;

import com.example.BeU2W3_Project.models.User;
import com.example.BeU2W3_Project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Utente con username " + username + " non trovato."));
    }
}
