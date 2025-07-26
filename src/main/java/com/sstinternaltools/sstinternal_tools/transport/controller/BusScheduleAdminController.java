package com.sstinternaltools.sstinternal_tools.transport.controller;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import com.sstinternaltools.sstinternal_tools.transport.facade.interfaces.BusScheduleFacade;
import com.sstinternaltools.sstinternal_tools.transport.mapper.interfaces.BusDtoMapper;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transport/schedule/admin")
public class BusScheduleAdminController {

    private BusScheduleFacade busScheduleFacade;
    private BusDtoMapper<BusSchedule, BusScheduleResponseDto, BusScheduleCreateDto, BusScheduleUpdateDto, BusScheduleSummaryDto> dtoMapper;

    public BusScheduleAdminController(BusScheduleFacade busScheduleFacade,BusDtoMapper<BusSchedule, BusScheduleResponseDto, BusScheduleCreateDto, BusScheduleUpdateDto, BusScheduleSummaryDto> dtoMapper) {
        this.busScheduleFacade = busScheduleFacade;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<BusScheduleResponseDto> createBusSchedule(@RequestBody @Valid BusScheduleCreateDto busScheduleCreateDto) {
        BusScheduleResponseDto responseDto = busScheduleFacade.createBusSchedule(busScheduleCreateDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update/{scheduleId}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<BusScheduleResponseDto> updateBusSchedule(@RequestBody @Valid BusScheduleUpdateDto busScheduleUpdateDto,@PathVariable @Valid Long scheduleId) {
        BusScheduleResponseDto updateDto=busScheduleFacade.updateBusSchedule(busScheduleUpdateDto,scheduleId);
        return ResponseEntity.ok(updateDto);
    }

    @DeleteMapping("/delete/{scheduleId}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<String> deleteBusSchedule(@PathVariable @Valid Long scheduleId) {
        busScheduleFacade.deleteBusSchedule(scheduleId);
        return ResponseEntity.ok("Schedule is deleted");
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<BusScheduleResponseDto>> getBusScheduleByDate(@PathVariable @Valid LocalDate date) {
        List<BusScheduleResponseDto> listSchedule=busScheduleFacade.getBusSchedule(date)
                .stream()
                .map(event -> dtoMapper.toResponseDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listSchedule);
    }

    @GetMapping("/getBy/date-range")
    public ResponseEntity<List<BusScheduleResponseDto>> searchByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<BusScheduleResponseDto> events= busScheduleFacade.searchBusScheduleByDateRange(start, end)
                .stream()
                .map(event -> dtoMapper.toResponseDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

}
