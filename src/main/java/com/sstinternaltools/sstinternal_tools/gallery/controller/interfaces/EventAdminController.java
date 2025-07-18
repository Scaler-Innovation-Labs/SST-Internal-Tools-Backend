package com.sstinternaltools.sstinternal_tools.gallery.controller.interfaces;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("event/admin")
public interface EventAdminController {
    @PostMapping("/create")
    ResponseEntity<EventResponseDto> createEvent(@RequestBody @Valid EventCreateDto eventCreateDto);

    @PutMapping("/update/{id}")
    ResponseEntity<EventResponseDto> updateEvent(@PathVariable @Valid Long id, @RequestBody  @Valid EventUpdateDto eventUpdateDto);

    @GetMapping("/getById/{id}")
    ResponseEntity<EventResponseDto> getEventById(@PathVariable @Valid Long id);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteEvent(@PathVariable @Valid Long id);

    @GetMapping("/getAllEvents")
    ResponseEntity<List<EventResponseDto>> getAllEvents();

    @GetMapping("/search")
    ResponseEntity<List<EventResponseDto>> searchByName(@RequestParam @Valid String name);

    @GetMapping("/getBy/date-range")
    ResponseEntity<List<EventResponseDto>> searchByDateRange(@Valid
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,@Valid
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end);

    @GetMapping("/getByDate")
    ResponseEntity<List<EventResponseDto>> searchByDate(@Valid
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

}
