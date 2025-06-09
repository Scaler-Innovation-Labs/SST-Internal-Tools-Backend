package com.sstinternaltools.sstinternal_tools.transport.service.interfaces;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;

import java.time.LocalDate;
import java.util.List;

public interface BusScheduleService {
     BusScheduleResponseDto createBusSchedule(BusScheduleCreateDto dto);
     BusScheduleResponseDto updateBusSchedule(BusScheduleUpdateDto dto,Long scheduleId);
     void deleteBusSchedule(Long id);
     List<BusSchedule> getSchedulesForDate(LocalDate date);
     public void deleteOldSchedules();
}
