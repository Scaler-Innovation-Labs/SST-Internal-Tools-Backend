package com.sstinternaltools.sstinternal_tools.gallery.controller.interfaces;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventSummaryDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/event/user")
public interface EventUserController {

    @GetMapping
    ResponseEntity<List<EventSummaryDto>> getAllEvents();

    @GetMapping("/search")
    ResponseEntity<List<EventSummaryDto>> searchByName(@RequestParam String name);

    @GetMapping("getBy/date-range")
    ResponseEntity<List<EventSummaryDto>> searchByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end);

    @GetMapping("/getByDate")
    ResponseEntity<List<EventSummaryDto>> searchByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

}
