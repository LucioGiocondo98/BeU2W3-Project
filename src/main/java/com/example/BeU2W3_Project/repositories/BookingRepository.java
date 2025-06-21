package com.example.BeU2W3_Project.repositories;

import com.example.BeU2W3_Project.models.Booking;
import com.example.BeU2W3_Project.models.Event;
import com.example.BeU2W3_Project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    boolean existsByUserAndEvent(User user, Event event);
}
