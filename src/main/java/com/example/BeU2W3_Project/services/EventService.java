package com.example.BeU2W3_Project.services;

import com.example.BeU2W3_Project.dto.CreateEventDTO;
import com.example.BeU2W3_Project.exceptions.ResourceNotFoundException;
import com.example.BeU2W3_Project.exceptions.UnauthorizedOperationException;
import com.example.BeU2W3_Project.models.Event;
import com.example.BeU2W3_Project.models.User;
import com.example.BeU2W3_Project.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepo;

    public List<Event> findAllEvents(){
        return eventRepo.findAll();
    }
    public Event findById(int id){
        return eventRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Evento con id" +id + " non trovato"));
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
        return eventRepo.save(newEvent);
    }

    public Event updateEvent(int id, CreateEventDTO payload, User currentUser) {
        Event event = this.findEventById(id);
        if (event.getOrganizer().getId() != currentUser.getId()) {
            throw new UnauthorizedOperationException("Non sei l'organizzatore di questo evento. Non puoi modificarlo.");
        }
        event.setTitle(payload.getTitle());
        event.setDescription(payload.get());
        event.setEventDate(payload.getEventDate());
        event.setLocation(payload.getLocation());
        return eventRepo.save(event);
    }

    public void deleteEvent(int id, User currentUser) {
        Event event = this.findEventById(id);
        if (event.getOrganizer().getId() != currentUser.getId()) {
            throw new UnauthorizedOperationException("Non sei l'organizzatore di questo evento. Non puoi eliminarlo.");
        }
        eventRepo.delete(event);
    }
}
