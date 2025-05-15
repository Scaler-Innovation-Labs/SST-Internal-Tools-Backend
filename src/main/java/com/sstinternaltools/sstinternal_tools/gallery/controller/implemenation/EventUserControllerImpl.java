package com.sstinternaltools.sstinternal_tools.gallery.controller.implemenation;

import com.sstinternaltools.sstinternal_tools.gallery.controller.interfaces.EventUserController;
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
public class EventUserControllerImpl implements EventUserController {
    private EventServiceFacade eventServiceFacade;
    private EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> eventDtoMapper;

    public EventUserControllerImpl(EventServiceFacade eventServiceFacade,EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> eventDtoMapper) {
        this.eventServiceFacade = eventServiceFacade;
        this.eventDtoMapper = eventDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<EventSummaryDto>> getAllEvents() {
        List<EventSummaryDto> events= eventServiceFacade. getAllEvents()
                .stream()
                .map(event -> eventDtoMapper.toSummaryDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventSummaryDto>> searchByName(@RequestParam String name) {
        List<EventSummaryDto> events= eventServiceFacade.searchEventsByName(name)
                .stream()
                .map(event -> eventDtoMapper.toSummaryDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<EventSummaryDto>> searchByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<EventSummaryDto> events= eventServiceFacade.searchEventsByDateRange(start, end)
                .stream()
                .map(event -> eventDtoMapper.toSummaryDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/date")
    public ResponseEntity<List<EventSummaryDto>> searchByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<EventSummaryDto> events= eventServiceFacade.searchEventsByDate(date)
                .stream()
                .map(event -> eventDtoMapper.toSummaryDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }
}
