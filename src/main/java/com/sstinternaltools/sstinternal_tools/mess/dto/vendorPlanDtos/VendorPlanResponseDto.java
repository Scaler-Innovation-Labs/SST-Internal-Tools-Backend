package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos;

import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class VendorPlanResponseDto {
    @NotNull(message = "Plan Id cannot be blank")
    private Long vendorPlanId;
    @NotBlank(message = "Plan name cannot be blank")
    private String planName;
    @NotNull(message = "Vendor id cannot be blank")
    private Long vendorId; // Only the ID, not the whole Vendor entity
    @NotNull(message = "Plan fee cannot be blank")
    private Double fee;
    @NotNull(message = "Plan meal type cannot be blank")
    @NotEmpty(message = "Plan meal type set cannot be empty")
    private Set<MealType> mealTypes;

    public Long getVendorPlanId() {
        return vendorPlanId;
    }

    public void setVendorPlanId(Long vendorPlanId) {
        this.vendorPlanId = vendorPlanId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
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
