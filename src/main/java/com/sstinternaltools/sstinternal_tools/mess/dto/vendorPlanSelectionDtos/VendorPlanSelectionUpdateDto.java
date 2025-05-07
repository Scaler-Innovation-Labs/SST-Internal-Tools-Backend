package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import jakarta.validation.constraints.NotNull;

public class VendorPlanSelectionUpdateDto {

    @NotNull(message = "Plan Id cannot be blank.")
    private Long planId;

    public VendorPlanSelectionUpdateDto() {}

    public VendorPlanSelectionUpdateDto(Long planId) {
        this.planId = planId;
    }

    public Long getPlanId() {
        System.out.println(planId);
        return planId;
    }

    public void setPlan(Long planId) {
        this.planId = planId;
    }
}
