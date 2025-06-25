package com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionHistoryDtos.VendorPlanSelectionHistorySummaryDto;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface VendorPlanSelectionHistoryService {
    void createHistoryForMonth(LocalDate month);
    void updateSelectionHistoryIfWithinWindow(User user);
    List<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByUserId(Long userId);
    List<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByUserIdAndMonth(Long userId, LocalDate month);
    Page<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByMonthAndVendorPlan(LocalDate date, String vendorPlan, Pageable pageable);
    Page<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByMonthAndVendor(LocalDate date, String vendorName, Pageable pageable);
    Page<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByMonth(LocalDate date, Pageable pageable);
    Page<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistory(Pageable pageable);
}
