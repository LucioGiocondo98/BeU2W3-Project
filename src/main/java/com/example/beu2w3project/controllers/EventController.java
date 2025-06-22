package com.example.beu2w3project.controllers;

import com.example.beu2w3project.dto.CreateEventDTO;
import com.example.beu2w3project.models.Event;
import com.example.beu2w3project.models.User;
import com.example.beu2w3project.services.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
        return eventService.findEventById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<Event> createEvent(@RequestBody @Valid CreateEventDTO payload, @AuthenticationPrincipal User currentUser) {
        Event newEvent = eventService.createEvent(payload, currentUser);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public Event updateEvent(@PathVariable int id, @RequestBody @Valid CreateEventDTO payload, @AuthenticationPrincipal User currentUser) {
        return eventService.updateEvent(id, payload, currentUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable int id, @AuthenticationPrincipal User currentUser) {
        eventService.deleteEvent(id, currentUser);
    }
}
