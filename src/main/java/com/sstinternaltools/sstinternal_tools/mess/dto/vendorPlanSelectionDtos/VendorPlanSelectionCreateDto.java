package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import jakarta.validation.constraints.NotBlank;

import java.time.YearMonth;

public class VendorPlanSelectionCreateDto {

    @NotBlank(message = "Vendor plan id cannot be blank")
    private Long vendorPlanId;
    @NotBlank(message = "User id cannot be blank")
    private Long userId;
    @NotBlank(message = "Month cannot be blank")
    private YearMonth selectedMonth;

    public VendorPlanSelectionCreateDto() {}

    public VendorPlanSelectionCreateDto(Long vendorPlanId,Long userId, YearMonth selectedMonth) {
        this.vendorPlanId = vendorPlanId;
        this.userId = userId;
        this.selectedMonth = selectedMonth;
    }

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