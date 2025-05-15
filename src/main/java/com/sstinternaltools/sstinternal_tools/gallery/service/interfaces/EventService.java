package com.sstinternaltools.sstinternal_tools.gallery.service.interfaces;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    Event addEvent(EventCreateDto eventCreateDto);
    List<Event> getAllEvents();
    List<Event> searchEventsByName(String name);
    List<Event> searchEventsByDateRange(LocalDate start, LocalDate end);
    Event getEventById(Long id);
    List<Event> searchEventsByDate(LocalDate date);
    void deleteEvent(Long id);
}
