package com.sstinternaltools.sstinternal_tools.transport.service.interfaces;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;

import java.time.LocalDate;
import java.util.List;

public interface BusScheduleService {
     BusScheduleResponseDto createBusSchedule(BusScheduleCreateDto dto);
     BusScheduleSummaryDto updateBusSchedule(BusScheduleUpdateDto dto);
     void deleteBusSchedule(Long id);
     List<BusScheduleResponseDto> getSchedulesForDate(LocalDate date);
}
