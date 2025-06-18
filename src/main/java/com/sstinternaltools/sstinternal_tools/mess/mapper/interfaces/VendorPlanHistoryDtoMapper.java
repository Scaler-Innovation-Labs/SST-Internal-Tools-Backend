package com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanHistoryDtos.VendorPlanHistorySummaryDto;

public interface VendorPlanHistoryDtoMapper <VendorPlanHistory> {
    VendorPlanHistorySummaryDto toSummaryDto(VendorPlanHistory vendorPlanHistory);
}
