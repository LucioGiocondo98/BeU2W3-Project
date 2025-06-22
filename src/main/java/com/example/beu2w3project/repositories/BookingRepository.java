package com.example.beu2w3project.repositories;

import com.example.beu2w3project.models.Booking;
import com.example.beu2w3project.models.Event;
import com.example.beu2w3project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    boolean existsByUserAndEvent(User user, Event event);
    List<Booking> findByUser(User user);
}
