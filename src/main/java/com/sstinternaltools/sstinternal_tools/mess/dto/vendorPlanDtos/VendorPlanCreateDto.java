package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos;

import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class VendorPlanCreateDto {
    @NotBlank(message = "Plan name cannot be blank")
    private String planName;
    @NotBlank(message = "Vendor id cannot be blank")
    private Long vendorId; // Only the ID, not the whole Vendor entity
    @NotBlank(message = "Plan fee cannot be blank")
    private Double fee;
    @NotBlank(message = "Plan meal type cannot be blank")
    private Set<MealType> mealTypes;

    public VendorPlanCreateDto() {}

    public VendorPlanCreateDto(String planName, Set<MealType> mealTypes, Long vendorId, Double fee) {
        this.planName = planName;
        this.mealTypes = mealTypes;
        this.vendorId = vendorId;
        this.fee = fee;
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