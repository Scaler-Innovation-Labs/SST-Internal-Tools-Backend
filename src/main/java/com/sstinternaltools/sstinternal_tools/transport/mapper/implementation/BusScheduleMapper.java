package com.sstinternaltools.sstinternal_tools.transport.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusStatus;
import com.sstinternaltools.sstinternal_tools.transport.mapper.interfaces.BusDtoMapper;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class BusScheduleMapper implements BusDtoMapper<BusSchedule, BusScheduleResponseDto, BusScheduleCreateDto, BusScheduleUpdateDto, BusScheduleSummaryDto> {
    @Override
    public BusSchedule fromCreateDto(BusScheduleCreateDto createDto, DayOfWeek dayOfWeek) {
        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setSource(createDto.getSource());
        busSchedule.setDestination(createDto.getDestination());
        busSchedule.setDepartureTime(createDto.getDepartureTime());
        busSchedule.setDate(createDto.getDate());
        busSchedule.setArrivalTime(createDto.getArrivalTime());
        busSchedule.setDayOfWeek(dayOfWeek);
        busSchedule.setStudentsBoarded(0);
        busSchedule.setBusStatus(BusStatus.SCHEDULED);
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
            DayOfWeek day = updateDto.getDate().getDayOfWeek();
            busSchedule.setDayOfWeek(day);
        }
        if(updateDto.getSource() != null) {
            busSchedule.setSource(updateDto.getSource());
        }
        if(updateDto.getArrivalTime() != null) {
            busSchedule.setArrivalTime(updateDto.getArrivalTime());
        }
        if(updateDto.getBusStatus() != null) {
            busSchedule.setBusStatus(updateDto.getBusStatus());
        }
        if(updateDto.getStudentsBoarded() > 0) {
            busSchedule.setStudentsBoarded(updateDto.getStudentsBoarded());
        }
        return busSchedule;
    }

    @Override
    public BusScheduleResponseDto toResponseDto(BusSchedule busSchedule) {
        BusScheduleResponseDto responseDto = new BusScheduleResponseDto();
        responseDto.setDepartureTime(busSchedule.getDepartureTime());
        responseDto.setDestination(busSchedule.getDestination());
        responseDto.setArrivalTime(busSchedule.getArrivalTime());
        responseDto.setDayofWeek(busSchedule.getDayOfWeek());
        responseDto.setSource(busSchedule.getSource());
        responseDto.setDate(busSchedule.getDate());
        responseDto.setId(busSchedule.getId());
        responseDto.setBusStatus(busSchedule.getBusStatus());
        responseDto.setStudentsBoarded(busSchedule.getStudentsBoarded());
        return responseDto;
    }

    @Override
    public BusScheduleSummaryDto toSummaryDto(BusSchedule busSchedule) {
        BusScheduleSummaryDto summaryDto = new BusScheduleSummaryDto();
        summaryDto.setDepartureTime(busSchedule.getDepartureTime());
        summaryDto.setDestination(busSchedule.getDestination());
        summaryDto.setSource(busSchedule.getSource());
        summaryDto.setArrivalTime(busSchedule.getArrivalTime());
        summaryDto.setDayofWeek(busSchedule.getDayOfWeek());
        summaryDto.setDate(busSchedule.getDate());
        summaryDto.setBusStatus(busSchedule.getBusStatus());
        return summaryDto;
    }
}
