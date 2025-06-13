package com.sstinternaltools.sstinternal_tools.transport.controller;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import com.sstinternaltools.sstinternal_tools.transport.facade.interfaces.BusScheduleFacade;
import com.sstinternaltools.sstinternal_tools.transport.mapper.interfaces.BusDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transport/schedule/user")
public class BusScheduleUserController {

    private BusScheduleFacade busScheduleFacade;
    private BusDtoMapper<BusSchedule, BusScheduleResponseDto, BusScheduleCreateDto, BusScheduleUpdateDto, BusScheduleSummaryDto> dtoMapper;

    public BusScheduleUserController(BusScheduleFacade busScheduleFacade,BusDtoMapper<BusSchedule, BusScheduleResponseDto, BusScheduleCreateDto, BusScheduleUpdateDto, BusScheduleSummaryDto> dtoMapper) {
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
