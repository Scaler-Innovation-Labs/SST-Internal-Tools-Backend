package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos;

public class VendorPlanUpdateDto {

    private String planName;
    private Double fee;

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
