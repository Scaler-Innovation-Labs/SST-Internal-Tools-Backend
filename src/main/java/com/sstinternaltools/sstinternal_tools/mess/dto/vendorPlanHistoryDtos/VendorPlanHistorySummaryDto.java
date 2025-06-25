package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanHistoryDtos;

import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class VendorPlanHistorySummaryDto {
    @NotBlank(message = "Plan name cannot be blank")
    private String planName;
    @NotBlank(message = "Vendor name cannot be blank")
    private String vendorName;
    @NotNull(message = "Plan fee cannot be blank")
    private Double fee;
    @NotNull(message = "Plan meal type cannot be blank")
    @NotEmpty(message = "Plan meal type set cannot be empty")
    private Set<MealType> mealTypes;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Set<MealType> getMealTypes() {
        return mealTypes;
    }

    public void setMealTypes(Set<MealType> mealTypes) {
        this.mealTypes = mealTypes;
    }
}
