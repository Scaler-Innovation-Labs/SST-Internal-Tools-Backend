package com.sstinternaltools.sstinternal_tools.gallery.controller.implemenation;

import com.sstinternaltools.sstinternal_tools.gallery.controller.interfaces.EventAdminController;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventSummaryDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.gallery.facade.interfaces.EventServiceFacade;
import com.sstinternaltools.sstinternal_tools.gallery.mapper.interfaces.EventDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EventUpdateDto> updateEvent(@PathVariable Long id, @RequestBody EventUpdateDto eventUpdateDto) {

    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {

    }

    @GetMapping("/search")
    public List<Event> searchByName(@RequestParam String name) {
        return eventService.searchEventsByName(name);
    }

    @GetMapping("/date-range")
    public List<Event> searchByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return eventService.searchEventsByDateRange(start, end);
    }

    @GetMapping("/date")
    public List<Event> searchByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return eventService.searchEventsByDate(date);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
