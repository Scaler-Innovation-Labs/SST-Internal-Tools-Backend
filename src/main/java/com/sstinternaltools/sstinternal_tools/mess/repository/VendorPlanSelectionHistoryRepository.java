package com.sstinternaltools.sstinternal_tools.mess.repository;

import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelectionHistory;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendorPlanSelectionHistoryRepository extends JpaRepository<VendorPlanSelectionHistory, Long> {
    void deleteByUserAndSelectedMonth(User user, LocalDate month);
    List<VendorPlanSelectionHistory> findByUserId(Long userId);
    List<VendorPlanSelectionHistory> findByUserIdAndSelectedMonth(Long userId, LocalDate month);
    Page<VendorPlanSelectionHistory> findAll(Pageable pageable);
    Page<VendorPlanSelectionHistory> findBySelectedMonth(LocalDate month, Pageable pageable);
    Page<VendorPlanSelectionHistory> findBySelectedMonthAndPlan_PlanName(LocalDate month, String vendorPlan, Pageable pageable);
    Page<VendorPlanSelectionHistory> findBySelectedMonthAndPlan_VendorName(LocalDate month, String vendorName, Pageable pageable);

}
