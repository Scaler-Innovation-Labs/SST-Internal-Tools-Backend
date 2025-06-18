package com.sstinternaltools.sstinternal_tools.mess.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanHistoryDtos.VendorPlanHistorySummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanHistory;
import com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces.VendorPlanHistoryDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class VendorPlanHistoryMapper implements VendorPlanHistoryDtoMapper<VendorPlanHistory> {
    @Override
    public VendorPlanHistorySummaryDto toSummaryDto(VendorPlanHistory vendorPlanHistory) {
        VendorPlanHistorySummaryDto vendorPlanHistorySummaryDto = new VendorPlanHistorySummaryDto();
        vendorPlanHistorySummaryDto.setVendorName(vendorPlanHistory.getVendorName());
        vendorPlanHistorySummaryDto.setPlanName(vendorPlanHistory.getPlanName());
        vendorPlanHistorySummaryDto.setMealTypes(vendorPlanHistory.getMealTypes());
        vendorPlanHistorySummaryDto.setFee(vendorPlanHistory.getFee());
        return vendorPlanHistorySummaryDto;
    }
}
