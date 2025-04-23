package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos;

import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class VendorPlanSummaryDto {

    @NotBlank(message = "Plan name cannot be blank")
    private String planName;
    @NotBlank(message = "Vendor id cannot be blank")
    private String vendorName; // Only the ID, not the whole Vendor entity
    @NotBlank(message = "Plan fee cannot be blank")
    private Double fee;
    @NotBlank(message = "Plan meal type cannot be blank")
    private Set<MealType> mealTypes;

    public VendorPlanSummaryDto() {}

    public VendorPlanSummaryDto(String planName,String vendorName, Set<MealType> mealTypes, Double fee) {
        this.planName = planName;
        this.vendorName = vendorName;
        this.mealTypes = mealTypes;
        this.fee = fee;
    }


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
