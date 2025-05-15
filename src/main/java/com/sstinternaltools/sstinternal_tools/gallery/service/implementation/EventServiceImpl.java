package com.sstinternaltools.sstinternal_tools.gallery.service.implementation;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventSummaryDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.gallery.mapper.interfaces.EventDtoMapper;
import com.sstinternaltools.sstinternal_tools.gallery.repository.EventRepository;
import com.sstinternaltools.sstinternal_tools.gallery.service.interfaces.EventService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> eventDtoMapper;

    public EventServiceImpl(EventRepository eventRepository, EventDtoMapper eventDtoMapper) {
        this.eventRepository = eventRepository;
        this.eventDtoMapper = eventDtoMapper;
    }

    public Event addEvent(EventCreateDto eventCreateDto) {
        Event event = eventDtoMapper.fromCreateDto(eventCreateDto);
        Event savedEvent = eventRepository.save(event);
        return savedEvent;

    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> searchEventsByName(String name) {
        return eventRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Event> searchEventsByDateRange(LocalDate start, LocalDate end) {
        return eventRepository.findByDateBetween(start, end);
    }

    public Event getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new ResourceAccessException("Event with this "+id+" not found"));
        return event;
    }

    public List<Event> searchEventsByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
