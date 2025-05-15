package com.sstinternaltools.sstinternal_tools.gallery.controller.implemenation;

import com.sstinternaltools.sstinternal_tools.gallery.controller.interfaces.EventAdminController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventAdminControllerImpl implements EventAdminController {

//    private EventService eventService;
//
//    public EventAdminControllerImpl(EventService eventService) {
//        this.eventService = eventService;
//    }
//
//    @PostMapping("create")
//    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventCreateDto eventCreateDto) {
//        EventResponseDto eventResponseDto = eventService.addEvent(eventCreateDto);
//        return ResponseEntity.ok(eventResponseDto);
//    }
//
//    @PutMapping("update/{id}")
//    public ResponseEntity<EventUpdateDto> updateEvent(@PathVariable Long id, @RequestBody EventUpdateDto eventUpdateDto) {
//
//    }
//
//    @GetMapping
//    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
//
//    }
//
//    @GetMapping("/search")
//    public List<Event> searchByName(@RequestParam String name) {
//        return eventService.searchEventsByName(name);
//    }
//
//    @GetMapping("/date-range")
//    public List<Event> searchByDateRange(
//            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
//            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
//        return eventService.searchEventsByDateRange(start, end);
//    }
//
//    @GetMapping("/date")
//    public List<Event> searchByDate(
//            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        return eventService.searchEventsByDate(date);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteEvent(@PathVariable Long id) {
//        eventService.deleteEvent(id);
//    }
}
