package com.example.beu2w3project.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CreateEventDTO {
    @NotBlank(message = "Il titolo non può essere vuoto.")
    @Size(min = 3, max = 100, message = "Il titolo deve avere tra 3 e 100 caratteri.")
    private String title;
    @NotBlank(message = "La descrizione non può essere vuota.")
    private String description;
    @Future(message = "La data dell'evento deve essere nel futuro.")
    @NotNull(message = "La data non può essere nulla.")
    private LocalDateTime eventDate;
    @NotBlank(message = "Il luogo non può essere vuoto.")
    private String location;
    @Min(value = 1, message = "Ci deve essere almeno un posto disponibile.")
    @NotNull(message = "Il numero di posti totali è obbligatorio.")
    private Integer totalSeats;
}
