package com.example.beu2w3project.controllers;

import com.example.beu2w3project.models.Booking;
import com.example.beu2w3project.models.User;
import com.example.beu2w3project.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/my-bookings")
    @PreAuthorize("hasRole('USER')")
    public List<Booking> getMyBookings(@AuthenticationPrincipal User user){
        return bookingService.findBookingByUser(user);
    }
    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable int bookingId, @AuthenticationPrincipal User user) {
        bookingService.cancelBooking(bookingId,user);
    }
}
