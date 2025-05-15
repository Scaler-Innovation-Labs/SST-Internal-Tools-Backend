package com.sstinternaltools.sstinternal_tools.gallery.facade.interfaces;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventServiceFacade {
    Event addEvent(EventCreateDto eventCreateDto);
    EventResponseDto updateEvent(Long id, EventUpdateDto eventUpdateDto);
    List<Event> getAllEvents();
    List<Event> searchEventsByName(String name);
    List<Event> searchEventsByDateRange(LocalDate start, LocalDate end);
    Event getEventById(Long id);
    List<Event> searchEventsByDate(LocalDate date);
    void deleteEvent(Long id);
}
