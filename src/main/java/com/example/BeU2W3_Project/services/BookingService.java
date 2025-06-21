package com.example.BeU2W3_Project.services;
import com.example.BeU2W3_Project.exceptions.BookingAlreadyExistsException;
import com.example.BeU2W3_Project.exceptions.NoSeatsAvailableException;
import com.example.BeU2W3_Project.exceptions.ResourceNotFoundException;
import com.example.BeU2W3_Project.models.Booking;
import com.example.BeU2W3_Project.models.Event;
import com.example.BeU2W3_Project.models.User;
import com.example.BeU2W3_Project.repositories.BookingRepository;
import com.example.BeU2W3_Project.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public Booking createBooking(int eventId, User user) {
        Event event = eventRepository.findByIdForUpdate(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento con ID " + eventId + " non trovato."));

        if (event.getAvailableSeats() <= 0) {
            throw new NoSeatsAvailableException("Nessun posto disponibile per l'evento: " + event.getTitle());
        }

        if (bookingRepository.existsByUserAndEvent(user, event)) {
            throw new BookingAlreadyExistsException("Hai già una prenotazione per questo evento.");
        }

        event.decreaseAvailableSeats();
        eventRepository.save(event);

        Booking newBooking = new Booking(user, event);
        return bookingRepository.save(newBooking);
    }
}
