package com.example.beu2w3project.services;

import com.example.beu2w3project.dto.CreateEventDTO;
import com.example.beu2w3project.models.Event;
import com.example.beu2w3project.models.User;
import com.example.beu2w3project.exceptions.ResourceNotFoundException;
import com.example.beu2w3project.exceptions.UnauthorizedOperationException;
import com.example.beu2w3project.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event findEventById(int id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento con ID " + id + " non trovato."));
    }

    public Event createEvent(CreateEventDTO payload, User organizer) {
        Event newEvent = new Event();
        newEvent.setTitle(payload.getTitle());
        newEvent.setDescription(payload.getDescription());
        newEvent.setEventDate(payload.getEventDate());
        newEvent.setLocation(payload.getLocation());
        newEvent.setTotalSeats(payload.getTotalSeats());
        newEvent.setAvailableSeats(payload.getTotalSeats());
        newEvent.setOrganizer(organizer);
        return eventRepository.save(newEvent);
    }

    public Event updateEvent(int id, CreateEventDTO payload, User currentUser) {
        Event event = this.findEventById(id);
        if (event.getOrganizer().getId() != currentUser.getId()) {
            throw new UnauthorizedOperationException("Non sei l'organizzatore di questo evento. Non puoi modificarlo.");
        }
        event.setTitle(payload.getTitle());
        event.setDescription(payload.getDescription());
        event.setEventDate(payload.getEventDate());
        event.setLocation(payload.getLocation());
        return eventRepository.save(event);
    }

    public void deleteEvent(int id, User currentUser) {
        Event event = this.findEventById(id);
        if (event.getOrganizer().getId() != currentUser.getId()) {
            throw new UnauthorizedOperationException("Non sei l'organizzatore di questo evento. Non puoi eliminarlo.");
        }
        eventRepository.delete(event);
    }
}