package com.example.beu2w3project.services;

import com.example.beu2w3project.exceptions.ResourceNotFoundException;
import com.example.beu2w3project.models.User;
import com.example.beu2w3project.repositories.UserRepository;
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
