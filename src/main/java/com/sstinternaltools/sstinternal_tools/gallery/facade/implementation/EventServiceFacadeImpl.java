package com.sstinternaltools.sstinternal_tools.gallery.facade.implementation;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.gallery.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceFacadeImpl {
    private EventService eventService;

    public EventServiceFacadeImpl(EventService eventService) {
        this.eventService = eventService;
    }

    Event addEvent(EventCreateDto eventCreateDto){
        return eventService.addEvent(eventCreateDto);
    };
    List<Event> getAllEvents(){
        return eventService.getAllEvents();
    };
    List<Event> searchEventsByName(String name){
        return eventService.searchEventsByName(name);
    };

    List<Event> searchEventsByDateRange(LocalDate start, LocalDate end){
        return eventService.searchEventsByDateRange(start, end);
    };
    Event getEventById(Long id){
        return eventService.getEventById(id);
    };
    List<Event> searchEventsByDate(LocalDate date){
        return eventService.searchEventsByDate(date);
    };

    void deleteEvent(Long id){
        eventService.deleteEvent(id);
    };
}
