package com.sstinternaltools.sstinternal_tools.transport.repository;

import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusSchedule;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {
    List<BusSchedule> findAllByDateOrderByDepartureTimeAsc(LocalDate date);
    List<BusSchedule> findAllByDate(LocalDate date);
    List<BusSchedule> findAllByDateBetweenOrderByDateAsc(LocalDate startDate, LocalDate endDate);
    boolean existsByDate(LocalDate date);
    void deleteById(Long id);
    @Modifying
    @Transactional
    @Query("DELETE FROM BusSchedule b WHERE b.date < :today OR (b.date = :today AND b.departureTime < :cutoff)")
    void deleteOldSchedules(@Param("today") LocalDate today, @Param("cutoff") LocalTime cutoff);


}
