package com.sstinternaltools.sstinternal_tools.gallery.service.implementation;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventSummaryDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.gallery.exception.EventNotFoundException;
import com.sstinternaltools.sstinternal_tools.gallery.mapper.interfaces.EventDtoMapper;
import com.sstinternaltools.sstinternal_tools.gallery.repository.EventRepository;
import com.sstinternaltools.sstinternal_tools.gallery.service.interfaces.EventService;
import com.sstinternaltools.sstinternal_tools.security.exception.InvalidCredentialsException;
import com.sstinternaltools.sstinternal_tools.security.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> eventDtoMapper;

    public EventServiceImpl(EventRepository eventRepository, EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> eventDtoMapper) {
        this.eventRepository = eventRepository;
        this.eventDtoMapper = eventDtoMapper;
    }

    public Event addEvent(EventCreateDto eventCreateDto) {
        Event event = eventDtoMapper.fromCreateDto(eventCreateDto);
        Event savedEvent = eventRepository.save(event);
        return savedEvent;

    }

    @Override
    public EventResponseDto updateEvent(Long id,EventUpdateDto eventUpdateDto) {
        Event event = eventRepository.findById(id).orElseThrow(()->new EventNotFoundException("Event not found"));
        Event updatedEvent= eventDtoMapper.fromUpdateDto(eventUpdateDto, event);
        Event savedEvent = eventRepository.save(updatedEvent);
        EventResponseDto eventResponseDto = eventDtoMapper.toResponseDto(savedEvent);
        return eventResponseDto;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEventsByName(String name) {
        return eventRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Event> searchEventsByDateRange(LocalDate start, LocalDate end) {
        if(start == null || end == null){
            throw new InvalidCredentialsException("Start date cannot be null");
        }
        return eventRepository.findByDateBetween(start, end);
    }

    @Override
    public Event getEventById(Long id) {

        if(id==null){
            throw new InvalidCredentialsException("Id cannot be null");
        }

        Event event = eventRepository.findById(id).orElseThrow(()-> new EventNotFoundException("Event with this id ("+id+") is not found"));
        return event;
    }

    @Override
    public List<Event> searchEventsByDate(LocalDate date) {
        if(date==null){
            throw new InvalidCredentialsException("Date cannot be null");
        }
        return eventRepository.findByDate(date);
    }

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        if(id==null){
            throw new InvalidCredentialsException("Id cannot be null");
        }
        eventRepository.deleteById(id);
    }
}
