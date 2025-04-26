package com.sstinternaltools.sstinternal_tools.transport.service.implementation;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import com.sstinternaltools.sstinternal_tools.transport.mapper.implementation.BusScheduleMapper;
import com.sstinternaltools.sstinternal_tools.transport.repository.BusScheduleRepository;
import com.sstinternaltools.sstinternal_tools.transport.service.interfaces.BusScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @Transactional
    public BusScheduleResponseDto createBusSchedule(BusScheduleCreateDto createDto) {
        BusSchedule busSchedule = busScheduleMapper.fromCreateDto(createDto);
        busScheduleRepository.save(busSchedule);
        return busScheduleMapper.toResponseDto(busSchedule);
    }

    @Override
    @Transactional
    public BusScheduleResponseDto updateBusSchedule(BusScheduleUpdateDto updateDto,Long scheduleId) {
        if(scheduleId == null){
            throw new IllegalArgumentException("Schedule id cannot be null");
        }

        BusSchedule busSchedule = busScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceAccessException("Schedule does not exist"));

        BusSchedule updatedBusSchedule=busScheduleMapper.fromUpdateDto(updateDto,busSchedule);
        busScheduleRepository.save(updatedBusSchedule);
        return busScheduleMapper.toResponseDto(busSchedule);
    }

    @Override
    @Transactional
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

        return busScheduleRepository.findAllByDateOrderByDepartureTimeAsc(date)
                .stream()
                .map(schedule -> busScheduleMapper.toResponseDto(schedule))
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(cron = "0 0 * * * ?", zone = "Asia/Kolkata") // Every hour at minute 0
    @Transactional
    public void deleteOldSchedules() {
        LocalDate today = LocalDate.now();
        LocalTime cutoff = LocalTime.now().minusHours(1);

        busScheduleRepository.deleteOldSchedules(today, cutoff);

        System.err.println("Schedule deletion completed at " + LocalTime.now());
    }
}
