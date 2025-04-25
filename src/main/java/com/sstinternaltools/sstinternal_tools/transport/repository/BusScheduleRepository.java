package com.sstinternaltools.sstinternal_tools.transport.repository;

import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {
    List<BusSchedule> findAllByDate(LocalDate date);
    boolean existsByDate(LocalDate date);
    void deleteByDateBefore(LocalDate cutoffDate);
    void deleteById(Long id);
}
