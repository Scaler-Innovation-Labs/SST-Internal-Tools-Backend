package com.sstinternaltools.sstinternal_tools.gallery.facade.implementation;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.gallery.facade.interfaces.EventServiceFacade;
import com.sstinternaltools.sstinternal_tools.gallery.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceFacadeImpl implements EventServiceFacade {
    private final EventService eventService;

    public EventServiceFacadeImpl(EventService eventService) {
        this.eventService = eventService;
    }

    public Event addEvent(EventCreateDto eventCreateDto){
        return eventService.addEvent(eventCreateDto);
    }

    @Override
    public EventResponseDto updateEvent(Long id, EventUpdateDto eventUpdateDto) {
        return eventService.updateEvent(id, eventUpdateDto);
    };

    @Override
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    };
    @Override
    public List<Event> searchEventsByName(String name){
        return eventService.searchEventsByName(name);
    };
    @Override
    public List<Event> searchEventsByDateRange(LocalDate start, LocalDate end){
        return eventService.searchEventsByDateRange(start, end);
    };
    @Override
    public Event getEventById(Long id){
        return eventService.getEventById(id);
    };
    @Override
    public List<Event> searchEventsByDate(LocalDate date){
        return eventService.searchEventsByDate(date);
    };
    @Override
    public void deleteEvent(Long id){
        eventService.deleteEvent(id);
    };
}
