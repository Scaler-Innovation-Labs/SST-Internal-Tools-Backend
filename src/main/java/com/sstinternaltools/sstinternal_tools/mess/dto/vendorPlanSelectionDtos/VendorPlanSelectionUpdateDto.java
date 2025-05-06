package com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos;

import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;

import java.time.YearMonth;

public class VendorPlanSelectionUpdateDto {

    private Long planId;

    public VendorPlanSelectionUpdateDto() {}

    public VendorPlanSelectionUpdateDto(YearMonth selectedMonth,Long planId) {
        this.planId = planId;
    }

    public Long getPlan() {
        return planId;
    }

    public void setPlan(Long planId) {
        this.planId = planId;
    }
}
