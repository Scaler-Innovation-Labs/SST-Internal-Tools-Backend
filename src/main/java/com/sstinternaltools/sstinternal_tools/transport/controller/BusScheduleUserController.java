package com.sstinternaltools.sstinternal_tools.transport.controller;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.facade.interfaces.BusScheduleFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BusScheduleUserController {

    private BusScheduleFacade busScheduleFacade;

    public BusScheduleUserController(BusScheduleFacade busScheduleFacade) {
        this.busScheduleFacade = busScheduleFacade;
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<BusScheduleSummaryDto>> getBusScheduleByDate(@PathVariable @Valid LocalDate date) {
        List<BusScheduleResponseDto> listSchedule=busScheduleFacade.getBusSchedule(date);
        return ResponseEntity.ok(listSchedule);
    }
}
