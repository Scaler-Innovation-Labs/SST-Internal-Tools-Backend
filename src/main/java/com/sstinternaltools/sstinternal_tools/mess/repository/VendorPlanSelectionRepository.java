package com.sstinternaltools.sstinternal_tools.mess.repository;

import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendorPlanSelectionRepository extends JpaRepository<VendorPlanSelection, Integer> {
    List<VendorPlanSelection> findByUserIdAndSelectedMonth(String userId, YearMonth selectedMonth);
    List<VendorPlanSelection> findByUserId(String userId);
    List<VendorPlanSelection> findBySelectedMonthAndPlanVendorId(YearMonth selectedMonth, Long vendorId);
}
