package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;

public class VendorPlanSelectionCreateDto {

    @NotBlank(message = "Vendor plan id cannot be blank")
    private Long vendorPlanId;
    @NotBlank(message = "User id cannot be blank")
    private Long userId;
    @NotNull(message = "Month cannot be blank")
    private YearMonth selectedMonth;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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