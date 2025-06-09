package com.sstinternaltools.sstinternal_tools.transport.facade.implementation;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import com.sstinternaltools.sstinternal_tools.transport.facade.interfaces.BusScheduleFacade;
import com.sstinternaltools.sstinternal_tools.transport.service.interfaces.BusScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BusScheduleFacadeImpl implements BusScheduleFacade {

    private BusScheduleService busScheduleService;

    public BusScheduleFacadeImpl(BusScheduleService busScheduleService) {
        this.busScheduleService = busScheduleService;
    }

    public BusScheduleResponseDto createBusSchedule(BusScheduleCreateDto busScheduleCreateDto){
        return busScheduleService.createBusSchedule(busScheduleCreateDto);
    }

    public BusScheduleResponseDto updateBusSchedule(BusScheduleUpdateDto busScheduleUpdateDto,Long scheduleId){
        return busScheduleService.updateBusSchedule(busScheduleUpdateDto,scheduleId);
    }

    public void deleteBusSchedule(Long scheduleId){
        busScheduleService.deleteBusSchedule(scheduleId);
    }

    public List<BusSchedule> getBusSchedule(LocalDate date){
        return busScheduleService.getSchedulesForDate(date);
    }
}
