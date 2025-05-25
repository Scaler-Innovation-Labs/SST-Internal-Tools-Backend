package com.sstinternaltools.sstinternal_tools.transport.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import com.sstinternaltools.sstinternal_tools.transport.mapper.interfaces.DtoMapper;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class BusScheduleMapper implements DtoMapper <BusSchedule, BusScheduleResponseDto, BusScheduleCreateDto, BusScheduleUpdateDto, BusScheduleSummaryDto> {
    @Override
    public BusSchedule fromCreateDto(BusScheduleCreateDto createDto, DayOfWeek dayOfWeek) {
        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setSource(createDto.getSource());
        busSchedule.setDestination(createDto.getDestination());
        busSchedule.setDepartureTime(createDto.getDepartureTime());
        busSchedule.setDate(createDto.getDate());
        busSchedule.setArrivalTime(createDto.getArrivalTime());
        busSchedule.setDayOfWeek(dayOfWeek);
        return busSchedule;
    }

    @Override
    public BusSchedule fromUpdateDto(BusScheduleUpdateDto updateDto, BusSchedule busSchedule) {
        if(updateDto.getDepartureTime() != null) {
            busSchedule.setDepartureTime(updateDto.getDepartureTime());
        }
        if(updateDto.getDestination() != null) {
            busSchedule.setDestination(updateDto.getDestination());
        }
        if(updateDto.getDate() != null) {
            busSchedule.setDate(updateDto.getDate());
            DayOfWeek day =busSchedule.getDayOfWeek();
            busSchedule.setDayOfWeek(day);
        }
        if(updateDto.getSource() != null) {
            busSchedule.setSource(updateDto.getSource());
        }
        if(updateDto.getArrivalTime() != null) {
            busSchedule.setArrivalTime(updateDto.getArrivalTime());
        }
        return busSchedule;
    }

    @Override
    public BusScheduleResponseDto toResponseDto(BusSchedule busSchedule) {
        BusScheduleResponseDto responseDto = new BusScheduleResponseDto();
        responseDto.setDepartureTime(busSchedule.getDepartureTime());
        responseDto.setDestination(busSchedule.getDestination());
        responseDto.setSource(busSchedule.getSource());
        responseDto.setDate(busSchedule.getDate());
        responseDto.setId(busSchedule.getId());
        return responseDto;
    }

    @Override
    public BusScheduleSummaryDto toSummaryDto(BusSchedule busSchedule) {
        BusScheduleSummaryDto summaryDto = new BusScheduleSummaryDto();
        summaryDto.setDepartureTime(busSchedule.getDepartureTime());
        summaryDto.setDestination(busSchedule.getDestination());
        summaryDto.setSource(busSchedule.getSource());
        summaryDto.setDate(busSchedule.getDate());
        return summaryDto;
    }
}
