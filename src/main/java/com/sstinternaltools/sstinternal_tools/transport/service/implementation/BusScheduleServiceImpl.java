package com.sstinternaltools.sstinternal_tools.transport.service.implementation;

import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.gallery.exception.EventNotFoundException;
import com.sstinternaltools.sstinternal_tools.security.entity.UserPrincipal;
import com.sstinternaltools.sstinternal_tools.security.exception.InvalidCredentialsException;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleCreateDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleUpdateDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import com.sstinternaltools.sstinternal_tools.transport.exception.TransportScheduleNotFound;
import com.sstinternaltools.sstinternal_tools.transport.mapper.implementation.BusScheduleMapper;
import com.sstinternaltools.sstinternal_tools.transport.repository.BusScheduleRepository;
import com.sstinternaltools.sstinternal_tools.transport.service.interfaces.BusScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.DayOfWeek;
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
        String email=((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getEmail();
        System.out.println(email);
        DayOfWeek dayOfWeek = createDto.getDate().getDayOfWeek();
        BusSchedule busSchedule = busScheduleMapper.fromCreateDto(createDto,dayOfWeek);
        busScheduleRepository.save(busSchedule);
        return busScheduleMapper.toResponseDto(busSchedule);
    }

    @Override
    @Transactional
    public BusScheduleResponseDto updateBusSchedule(BusScheduleUpdateDto updateDto,Long scheduleId) {
        if(scheduleId == null){
            throw new InvalidCredentialsException("Schedule id cannot be null");
        }

        BusSchedule busSchedule = busScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new TransportScheduleNotFound("Schedule does not exist"));

        BusSchedule updatedBusSchedule=busScheduleMapper.fromUpdateDto(updateDto,busSchedule);
        busScheduleRepository.save(updatedBusSchedule);
        return busScheduleMapper.toResponseDto(busSchedule);
    }

    @Override
    @Transactional
    public void deleteBusSchedule(Long scheduleId) {
        if(scheduleId == null){
            throw new InvalidCredentialsException("Schedule id cannot be null");
        }

        if(!busScheduleRepository.existsById(scheduleId)) {
            throw new TransportScheduleNotFound("Schedule does not exist");
        }

        busScheduleRepository.deleteById(scheduleId);
    }

    @Override
    public List<BusSchedule> getSchedulesForDate(LocalDate date) {
        if(date == null){
            throw new InvalidCredentialsException("Date cannot be null");
        }

        if(!busScheduleRepository.existsByDate(date)){
            throw new TransportScheduleNotFound("Schedule does not exist for "+date+".");
        }

        return busScheduleRepository.findAllByDateOrderByDepartureTimeAsc(date);
    }

    @Override
    public List<BusSchedule> searchBusScheduleByDateRange(LocalDate start, LocalDate end) {
        if(start == null || end == null){
            throw new InvalidCredentialsException("Start/end date cannot be null");
        }
        return busScheduleRepository.findAllByDateBetweenOrderByDateAsc(start, end);
    }

    @Override
    @Transactional
    public void deleteOldSchedules() {
        LocalDate today = LocalDate.now();
        LocalTime cutoff = LocalTime.now().minusHours(1);
        busScheduleRepository.deleteOldSchedules(today, cutoff);
    }
}
