package com.sstinternaltools.sstinternal_tools.gallery.controller.interfaces;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("event/admin")
public interface EventAdminController {
    @PostMapping("/create")
    ResponseEntity<EventResponseDto> createEvent(@RequestBody EventCreateDto eventCreateDto);

    @PutMapping("/update/{id}")
    ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id, @RequestBody EventUpdateDto eventUpdateDto);

    @GetMapping("/getById/{id}")
    ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteEvent(@PathVariable Long id);

    @GetMapping("/getAllEvents")
    ResponseEntity<List<EventResponseDto>> getAllEvents();

    @GetMapping("/search")
    ResponseEntity<List<EventResponseDto>> searchByName(@RequestParam String name);

    @GetMapping("/getBy/date-range")
    ResponseEntity<List<EventResponseDto>> searchByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end);

    @GetMapping("/getByDate")
    ResponseEntity<List<EventResponseDto>> searchByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

}
