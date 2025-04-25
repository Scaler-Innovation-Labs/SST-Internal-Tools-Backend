package com.sstinternaltools.sstinternal_tools.transport.repository;

import com.sstinternaltools.sstinternal_tools.transport.dto.BusScheduleResponseDto;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BusScheduleRepository extends JpaRepository {
    List<BusSchedule> findAllByDate(LocalDate date);
    BusSchedule findById(Long id);
    boolean existsByDate(LocalDate date);

}
