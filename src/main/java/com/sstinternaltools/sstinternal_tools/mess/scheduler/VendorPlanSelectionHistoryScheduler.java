package com.sstinternaltools.sstinternal_tools.mess.scheduler;

import com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface.VendorPlanSelectionHistoryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VendorPlanSelectionHistoryScheduler {

    private final VendorPlanSelectionHistoryService vendorPlanSelectionHistoryService;

    public VendorPlanSelectionHistoryScheduler(VendorPlanSelectionHistoryService vendorPlanSelectionHistoryService) {
        this.vendorPlanSelectionHistoryService = vendorPlanSelectionHistoryService;
    }

    @Scheduled(cron = "0 0 0 3 * ?") // Runs at 00:00 on the 3rd day of each month 0 0 0 3 * ?
    public void scheduledCreateHistory() {
        vendorPlanSelectionHistoryService.createHistoryForMonth(LocalDate.now().withDayOfMonth(1)); // Created the actual logic in service. So scheduler only runs/ triggers that mehtod.
    }
}

