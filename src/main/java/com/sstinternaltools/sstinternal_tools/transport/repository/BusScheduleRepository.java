package com.sstinternaltools.sstinternal_tools.transport.repository;

import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {
    List<BusSchedule> findAllByDateOrderByDepartureTimeAsc(LocalDate date);
    List<BusSchedule> findAllByDate(LocalDate date);
    boolean existsByDate(LocalDate date);
    void deleteByDateBefore(LocalDate cutoffDate);
    void deleteById(Long id);
    void deleteByDateAndDepartureTimeBefore(LocalDate date, LocalTime time);

}
