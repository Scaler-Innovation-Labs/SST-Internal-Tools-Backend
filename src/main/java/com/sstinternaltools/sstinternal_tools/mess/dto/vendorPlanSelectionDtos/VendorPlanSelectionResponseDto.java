package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import jakarta.validation.constraints.NotBlank;

import java.time.YearMonth;

public class VendorPlanSelectionResponseDto {
    @NotBlank(message = "Vendor plan selection name cannot be blank")
    private Long vendorPlanSelectionId;
    @NotBlank(message = "User Id cannot be blank")
    private Long userId;
    @NotBlank(message = "Plan Id cannot be blank")
    private Long vendorPlanId;
    @NotBlank(message = "Month cannot be blank")
    private YearMonth selectedMonth;

    public VendorPlanSelectionResponseDto() {}

    public VendorPlanSelectionResponseDto(Long vendorPlanSelectionId,Long userId, Long vendorPlanId, YearMonth selectedMonth) {
        this.vendorPlanSelectionId = vendorPlanSelectionId;
        this.userId = userId;
        this.vendorPlanId = vendorPlanId;
        this.selectedMonth = selectedMonth;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getVendorPlanSelectionId() {
        return vendorPlanSelectionId;
    }

    public void setVendorPlanSelectionId(Long vendorPlanSelectionId) {
        this.vendorPlanSelectionId = vendorPlanSelectionId;
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
