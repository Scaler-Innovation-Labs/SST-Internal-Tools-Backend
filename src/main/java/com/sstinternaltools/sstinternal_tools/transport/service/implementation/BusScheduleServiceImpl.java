package com.sstinternaltools.sstinternal_tools.transport.service.implementation;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleSummaryDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import com.sstinternaltools.sstinternal_tools.transport.mapper.implementation.BusScheduleMapper;
import com.sstinternaltools.sstinternal_tools.transport.repository.BusScheduleRepository;
import com.sstinternaltools.sstinternal_tools.transport.service.interfaces.BusScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {

    private final BusScheduleMapper busScheduleMapper;
    private final BusScheduleRepository busScheduleRepository;
    public BusScheduleServiceImpl(BusScheduleMapper busScheduleMapper, BusScheduleRepository busScheduleRepository) {
        this.busScheduleMapper = busScheduleMapper;
        this.busScheduleRepository = busScheduleRepository;
    }

    @Override
    public BusScheduleResponseDto createBusSchedule(BusScheduleCreateDto createDto) {
        BusSchedule busSchedule = busScheduleMapper.fromCreateDto(createDto);
        busScheduleRepository.save(busSchedule);
        return busScheduleMapper.toResponseDto(busSchedule);
    }

    @Override
    public BusScheduleResponseDto updateBusSchedule(BusScheduleUpdateDto updateDto,Long scheduleId) {
        if(scheduleId == null){
            throw new IllegalArgumentException("Schedule id cannot be null");
        }

        if(!busScheduleRepository.existsById(scheduleId)) {
            throw new ResourceAccessException("Schedule does not exist");
        }
        BusSchedule busSchedule=busScheduleRepository.findById(scheduleId);
        BusSchedule updatedBusSchedule=busScheduleMapper.fromUpdateDto(updateDto,busSchedule);
        busScheduleRepository.save(updatedBusSchedule);
        return busScheduleMapper.toResponseDto(busSchedule);
    }

    @Override
    public void deleteBusSchedule(Long scheduleId) {
        if(scheduleId == null){
            throw new IllegalArgumentException("Schedule id cannot be null");
        }

        if(!busScheduleRepository.existsById(scheduleId)) {
            throw new ResourceAccessException("Schedule does not exist");
        }

        busScheduleRepository.deleteById(scheduleId);
    }

    @Override
    public List<BusScheduleResponseDto> getSchedulesForDate(LocalDate date) {
        if(date == null){
            throw new IllegalArgumentException("Date cannot be null");
        }

        if(!busScheduleRepository.existsByDate(date)){
            throw new ResourceAccessException("Schedule does not exist for "+date+".");
        }

        return busScheduleRepository.findAllByDate(date)
                .stream()
                .map(schedule -> busScheduleMapper.toResponseDto(schedule))
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(cron = "0 0 2 1 */1 ?") //2 AM, 1st day, every month
    public void deleteOldSchedules() {
        LocalDate cutoffDate = LocalDate.now().minusMonths(1);
        busScheduleRepository.deleteByDateBefore(cutoffDate);
    }

}
