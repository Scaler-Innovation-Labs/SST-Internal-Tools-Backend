package com.sstinternaltools.sstinternal_tools.transport.mapper.interfaces;

import java.time.DayOfWeek;

public interface BusDtoMapper<BusSchedule, BusScheduleResponseDto, BusScheduleCreateDto, BusScheduleUpdateDto, BusScheduleSummaryDto> {

    BusSchedule fromCreateDto(BusScheduleCreateDto createDto, DayOfWeek dayOfWeek);
    BusSchedule fromUpdateDto(BusScheduleUpdateDto updateDto, BusSchedule busSchedule);
    BusScheduleResponseDto toResponseDto(BusSchedule busSchedule);
    BusScheduleSummaryDto toSummaryDto(BusSchedule busSchedule);
}
