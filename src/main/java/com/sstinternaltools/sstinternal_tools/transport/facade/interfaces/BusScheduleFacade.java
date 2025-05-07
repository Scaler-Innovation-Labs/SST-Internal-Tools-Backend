package com.sstinternaltools.sstinternal_tools.transport.facade.interfaces;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.service.interfaces.BusScheduleService;

import java.time.LocalDate;
import java.util.List;

public interface BusScheduleFacade {


    public BusScheduleResponseDto createBusSchedule(BusScheduleCreateDto busScheduleCreateDto);
    public BusScheduleResponseDto updateBusSchedule(BusScheduleUpdateDto busScheduleUpdateDto, Long scheduleId);
    public void deleteBusSchedule(Long scheduleId);
    public List<BusScheduleResponseDto> getBusSchedule(LocalDate date);
}
