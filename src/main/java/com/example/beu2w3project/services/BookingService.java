package com.example.beu2w3project.services;
import com.example.beu2w3project.exceptions.BookingAlreadyExistsException;
import com.example.beu2w3project.exceptions.NoSeatsAvailableException;
import com.example.beu2w3project.exceptions.ResourceNotFoundException;
import com.example.beu2w3project.exceptions.UnauthorizedOperationException;
import com.example.beu2w3project.models.Booking;
import com.example.beu2w3project.models.Event;
import com.example.beu2w3project.models.User;
import com.example.beu2w3project.repositories.BookingRepository;
import com.example.beu2w3project.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Booking> findBookingByUser(User user){
        return bookingRepository.findByUser(user);
    }
    @Transactional
    public void cancelBooking(int bookingId,User user){
        Booking booking= bookingRepository.findById(bookingId).orElseThrow(()->new ResourceNotFoundException("Prenotazione non trovata"));
        if (booking.getUser().getId()!= user.getId()){
            throw new UnauthorizedOperationException("Non hai i permessi per cancellare questa prenotazione");
        }
        Event event=booking.getEvent();
        event.setAvailableSeats(event.getAvailableSeats()+1);
        eventRepository.save(event);
        bookingRepository.delete(booking);
    }
}
