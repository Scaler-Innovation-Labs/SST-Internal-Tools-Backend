package com.sstinternaltools.sstinternal_tools.transport.service.implementation;

import com.sstinternaltools.sstinternal_tools.transport.service.interfaces.BusScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleCleanupScheduler {

    private final BusScheduleService busScheduleService;

    public ScheduleCleanupScheduler(BusScheduleService busScheduleService) {
        this.busScheduleService = busScheduleService;
    }

    @Scheduled(cron = "0 0 2 * * ?") // Every day at 2 AM
    public void scheduleSessionCleanup() {
       busScheduleService.deleteOldSchedules();
    }

}
