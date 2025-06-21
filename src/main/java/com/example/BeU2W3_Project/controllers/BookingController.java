package com.example.BeU2W3_Project.controllers;

import com.example.BeU2W3_Project.models.Booking;
import com.example.BeU2W3_Project.models.User;
import com.example.BeU2W3_Project.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Booking createBooking(@PathVariable int eventId, @AuthenticationPrincipal User currentUser) {
        return bookingService.createBooking(eventId, currentUser);
    }
}
