package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public class VendorPlanSelectionSummaryDto {

    @NotBlank(message = "Plan Name cannot be blank")
    private String vendorPlanName;
    @NotBlank(message = "Vendor name cannot be blank")
    private String vendorName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Month cannot be blank")
    private LocalDate selectedMonth;
    @NotNull(message = "Meal type cannot be blank")
    @NotEmpty(message = "Meal type cannot be empty")
    private Set<MealType> mealTypes;
    @NotNull(message = "Fee cannot be blank")
    private double fee;
    @NotNull(message = "Room number cannot be blank")
    private Integer roomNumber;

    public String getVendorPlanName() {
        return vendorPlanName;
    }

    public void setVendorPlanName(String vendorPlanName) {
        this.vendorPlanName = vendorPlanName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public LocalDate getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(LocalDate selectedMonth) {
        this.selectedMonth = LocalDate.of(selectedMonth.getYear(), selectedMonth.getMonth(), 1);
    }

    public  Set<MealType> getMealTypes() {
        return mealTypes;
    }

    public void setMealTypes(Set<MealType> mealTypes) {
        this.mealTypes = mealTypes;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
}
