package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionHistoryDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanHistoryDtos.VendorPlanHistorySummaryDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VendorPlanSelectionHistorySummaryDto {
    @NotNull(message = "User Id cannot be blank")
    private Long userId;
    @NotNull(message = "Plan Id cannot be blank")
    private VendorPlanHistorySummaryDto vendorPlanHistorySummaryDto;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Month cannot be blank")
    private LocalDate selectedMonth;
    @NotNull(message = "Room number cannot be blank")
    private Integer roomNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public VendorPlanHistorySummaryDto getVendorPlanHistorySummaryDto() {
        return vendorPlanHistorySummaryDto;
    }

    public void setVendorPlanHistorySummaryDto(VendorPlanHistorySummaryDto vendorPlanHistorySummaryDto) {
        this.vendorPlanHistorySummaryDto = vendorPlanHistorySummaryDto;
    }

    public LocalDate getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(LocalDate selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
}
