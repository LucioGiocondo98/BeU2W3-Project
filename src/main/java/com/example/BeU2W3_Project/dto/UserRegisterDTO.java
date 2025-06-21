package com.example.BeU2W3_Project.dto;

import com.example.BeU2W3_Project.enumeration.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "Username obbligatorio")
    @Size(min = 4,message = "Lo username deve contenere almeno 4 caratteri")
    private String username;

    @NotBlank(message = "Password obbligatoria")
    @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
    private String password;

    @NotNull(message = "Il ruolo è obbligatorio")
    private UserRole role;
}
