package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import jakarta.validation.constraints.NotBlank;

import java.time.YearMonth;
import java.util.Set;

public class VendorPlanSelectionSummaryDto {

    @NotBlank(message = "Plan Name cannot be blank")
    private String vendorPlanName;
    @NotBlank(message = "Vendor name cannot be blank")
    private String vendorName;
    @NotBlank(message = "Month cannot be blank")
    private YearMonth selectedMonth;
    @NotBlank(message = "Meal type cannot be blank")
    private Set<MealType> mealTypes;
    @NotBlank(message = "Fee cannot be blank")
    private double fee;

    public VendorPlanSelectionSummaryDto(){}

    public VendorPlanSelectionSummaryDto(String vendorPlanName, String vendorName, YearMonth selectedMonth, Set<MealType> mealTypes, double fee) {
        this.vendorPlanName = vendorPlanName;
        this.vendorName = vendorName;
        this.selectedMonth = selectedMonth;
        this.mealTypes = mealTypes;
        this.fee = fee;
    }

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

    public YearMonth getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(YearMonth selectedMonth) {
        this.selectedMonth = selectedMonth;
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
}
