package com.sstinternaltools.sstinternal_tools.transport.controller;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.facade.interfaces.BusScheduleFacade;
import com.sstinternaltools.sstinternal_tools.transport.mapper.interfaces.BusDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BusScheduleUserController {

    private BusScheduleFacade busScheduleFacade;
    private BusDtoMapper dtoMapper;

    public BusScheduleUserController(BusScheduleFacade busScheduleFacade,BusDtoMapper dtoMapper) {
        this.busScheduleFacade = busScheduleFacade;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<BusScheduleSummaryDto>> getBusScheduleByDate(@PathVariable @Valid LocalDate date) {
        List<BusScheduleSummaryDto> listSchedule=busScheduleFacade.getBusSchedule(date)
                .stream()
                .map(event -> dtoMapper.toSummaryDto(event))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listSchedule);
    }
}
