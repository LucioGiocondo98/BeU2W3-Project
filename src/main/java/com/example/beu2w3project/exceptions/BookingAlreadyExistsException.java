package com.example.beu2w3project.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookingAlreadyExistsException extends RuntimeException {
    public BookingAlreadyExistsException(String message) {
        super(message);
    }
}
