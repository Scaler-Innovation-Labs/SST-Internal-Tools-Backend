package com.sstinternaltools.sstinternal_tools.transport.controller;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.facade.interfaces.BusScheduleFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transport/schedule")
public class BusScheduleAdminController {

    private BusScheduleFacade busScheduleFacade;

    public BusScheduleAdminController(BusScheduleFacade busScheduleFacade) {
        this.busScheduleFacade = busScheduleFacade;
    }

    @PostMapping("/create")
    public ResponseEntity<BusScheduleResponseDto> createBusSchedule(@RequestBody @Valid BusScheduleCreateDto busScheduleCreateDto) {
        BusScheduleResponseDto responseDto = busScheduleFacade.createBusSchedule(busScheduleCreateDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update/{scheduleId}")
    public ResponseEntity<BusScheduleResponseDto> updateBusSchedule(@RequestBody @Valid BusScheduleUpdateDto busScheduleUpdateDto,@PathVariable @Valid Long scheduleId) {
        BusScheduleResponseDto updateDto=busScheduleFacade.updateBusSchedule(busScheduleUpdateDto,scheduleId);
        return ResponseEntity.ok(updateDto);
    }

    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<String> deleteBusSchedule(@PathVariable @Valid Long scheduleId) {
        busScheduleFacade.deleteBusSchedule(scheduleId);
        return ResponseEntity.ok("Schedule is deleted");
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<BusScheduleResponseDto>> getBusScheduleByDate(@PathVariable @Valid LocalDate date) {
        List<BusScheduleResponseDto> listSchedule=busScheduleFacade.getBusSchedule(date);
        return ResponseEntity.ok(listSchedule);
    }
}
