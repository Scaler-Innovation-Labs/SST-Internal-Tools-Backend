package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.YearMonth;

public class VendorPlanSelectionResponseDto {
    @NotNull(message = "Vendor plan selection name cannot be blank")
    private Long vendorPlanSelectionId;
    @NotNull(message = "User Id cannot be blank")
    private Long userId;
    @NotNull(message = "Plan Id cannot be blank")
    private Long vendorPlanId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Month cannot be blank")
    private LocalDate selectedMonth;

    public VendorPlanSelectionResponseDto() {}

    public VendorPlanSelectionResponseDto(Long vendorPlanSelectionId, Long userId, Long vendorPlanId, LocalDate selectedMonth) {
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

    public LocalDate getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(LocalDate selectedMonth) {
        this.selectedMonth = LocalDate.of(selectedMonth.getYear(), selectedMonth.getMonth(), 1);
    }
}
