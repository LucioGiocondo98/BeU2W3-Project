package com.example.beu2w3project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotBlank(message = "Username obbligatorio")
    private String username;
    @NotBlank(message = "Password obbligatoria")
    private String password;
}
