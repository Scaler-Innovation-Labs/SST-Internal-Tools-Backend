package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import jakarta.validation.constraints.NotBlank;

import java.time.YearMonth;

public class VendorPlanSelectionUpdateDtos {

    private Long vendorPlanId;
    private YearMonth selectedMonth;

    public VendorPlanSelectionUpdateDtos() {}

    public VendorPlanSelectionUpdateDtos(YearMonth selectedMonth, Long vendorPlanId) {
        this.selectedMonth = selectedMonth;
        this.vendorPlanId = vendorPlanId;
    }

    public Long getVendorPlanId() {
        return vendorPlanId;
    }

    public void setVendorPlanId(Long vendorPlanId) {
        this.vendorPlanId = vendorPlanId;
    }

    public YearMonth getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(YearMonth selectedMonth) {
        this.selectedMonth = selectedMonth;
    }
}
