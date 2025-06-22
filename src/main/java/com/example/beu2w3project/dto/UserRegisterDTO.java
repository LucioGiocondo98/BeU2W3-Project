package com.example.beu2w3project.dto;

import com.example.beu2w3project.enumeration.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
