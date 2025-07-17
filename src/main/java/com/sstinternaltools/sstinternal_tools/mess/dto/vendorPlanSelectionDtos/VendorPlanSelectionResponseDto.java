package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sstinternaltools.sstinternal_tools.mess.entity.Hostel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

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
    @NotNull(message = "Room number cannot be blank")
    private Integer roomNumber;
    @NotNull(message = "Hostel name cannot be blank")
    private Hostel hostel;

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

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
