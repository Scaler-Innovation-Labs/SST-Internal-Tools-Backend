package com.sstinternaltools.sstinternal_tools.mess.scheduler;

import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanSelectionAdminService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VendorPlanSelectionScheduler {

    private final VendorPlanSelectionAdminService vendorPlanSelectionAdminService;

    public VendorPlanSelectionScheduler(VendorPlanSelectionAdminService vendorPlanSelectionAdminService) {
        this.vendorPlanSelectionAdminService = vendorPlanSelectionAdminService;
    }

    @Scheduled(cron = "0 0 0 25 * ?") // Runs at 00:00 on the 3rd day of each month 0 0 0 3 * ?
    public void scheduledCreateHistory() {
        vendorPlanSelectionAdminService.deleteAllVendorPlanSelection(); // Created the actual logic in service. So scheduler only runs/ triggers that mehtod.
    }
}
