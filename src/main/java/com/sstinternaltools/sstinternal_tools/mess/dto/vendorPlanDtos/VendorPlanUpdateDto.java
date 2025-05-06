package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos;

import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;

import java.util.Set;

public class VendorPlanUpdateDto {

    private String planName;

    private Double fee;

    public VendorPlanUpdateDto() {}

    public VendorPlanUpdateDto(String planName, Set<MealType> mealTypes, Double fee) {
        this.planName = planName;
        this.fee = fee;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
