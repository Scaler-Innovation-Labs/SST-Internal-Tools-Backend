package com.sstinternaltools.sstinternal_tools.gallery.controller.implemenation;

import com.sstinternaltools.sstinternal_tools.gallery.controller.interfaces.EventAdminController;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventSummaryDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.gallery.facade.interfaces.EventServiceFacade;
import com.sstinternaltools.sstinternal_tools.gallery.mapper.interfaces.EventDtoMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/event/admin")
public class EventAdminControllerImpl implements EventAdminController {

    private EventServiceFacade eventServiceFacade;
    private EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> eventDtoMapper;

    public EventAdminControllerImpl(EventServiceFacade eventServiceFacade,EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> eventDtoMapper) {
        this.eventServiceFacade = eventServiceFacade;
        this.eventDtoMapper = eventDtoMapper;
    }

    @PostMapping("create")
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventCreateDto eventCreateDto) {
        Event event= eventServiceFacade.addEvent(eventCreateDto);
        EventResponseDto eventResponseDto= eventDtoMapper.toResponseDto(event);
        return ResponseEntity.ok(eventResponseDto);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id, @RequestBody EventUpdateDto eventUpdateDto) {
           EventResponseDto eventResponseDto=eventServiceFacade.updateEvent(id, eventUpdateDto);
           return ResponseEntity.ok(eventResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventServiceFacade.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<EventResponseDto> events= eventServiceFacade. getAllEvents()
                .stream()
                .map(event -> eventDtoMapper.toResponseDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventResponseDto>> searchByName(@RequestParam String name) {
        List<EventResponseDto> events= eventServiceFacade.searchEventsByName(name)
                .stream()
                .map(event -> eventDtoMapper.toResponseDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<EventResponseDto>> searchByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<EventResponseDto> events= eventServiceFacade.searchEventsByDateRange(start, end)
                .stream()
                .map(event -> eventDtoMapper.toResponseDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/date")
    public ResponseEntity<List<EventResponseDto>> searchByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<EventResponseDto> events= eventServiceFacade.searchEventsByDate(date)
                .stream()
                .map(event -> eventDtoMapper.toResponseDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

//    /api/events/date-range?start=2025-01-01&end=2025-01-31

}
