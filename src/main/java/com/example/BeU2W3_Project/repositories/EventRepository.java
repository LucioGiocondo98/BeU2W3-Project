package com.example.BeU2W3_Project.repositories;

import com.example.BeU2W3_Project.models.Event;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Event e Where e.id= :id")
    Optional<Event> findByIdForUpdate(int id);
}
