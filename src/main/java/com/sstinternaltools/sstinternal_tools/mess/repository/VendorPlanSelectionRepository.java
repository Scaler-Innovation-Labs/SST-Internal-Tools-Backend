package com.sstinternaltools.sstinternal_tools.mess.repository;

import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendorPlanSelectionRepository extends JpaRepository<VendorPlanSelection, Long> {
    List<VendorPlanSelection> findByUserIdAndSelectedMonth(Long userId, YearMonth selectedMonth);
    List<VendorPlanSelection> findByUserId(Long userId);
    List<VendorPlanSelection> findBySelectedMonthAndPlanVendorId(YearMonth selectedMonth, Long vendorId);
    List<VendorPlanSelection> findBySelectedMonth(YearMonth selectedMonth);
}
