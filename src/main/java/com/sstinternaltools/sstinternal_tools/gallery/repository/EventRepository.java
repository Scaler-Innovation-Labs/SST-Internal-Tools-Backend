package com.sstinternaltools.sstinternal_tools.gallery.repository;

import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByNameContainingIgnoreCase(String name);
    List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Event> findByDate(LocalDate date);
}
