package com.sstinternaltools.sstinternal_tools.mess.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionHistoryDtos.VendorPlanSelectionHistorySummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelectionHistory;
import com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces.VendorPlanSelectionHistoryDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class VendorPlanSelectionHistoryMapper implements VendorPlanSelectionHistoryDtoMapper<VendorPlanSelectionHistory> {

    private final VendorPlanHistoryMapper vendorPlanHistoryMapper;

    public VendorPlanSelectionHistoryMapper(VendorPlanHistoryMapper vendorPlanHistoryMapper) {
        this.vendorPlanHistoryMapper = vendorPlanHistoryMapper;
    }

    @Override
    public VendorPlanSelectionHistorySummaryDto toSummaryDto(VendorPlanSelectionHistory vendorPlanSelectionHistory) {
        VendorPlanSelectionHistorySummaryDto vendorPlanSelectionHistorySummaryDto = new VendorPlanSelectionHistorySummaryDto();
        vendorPlanSelectionHistorySummaryDto.setUserId(vendorPlanSelectionHistory.getUser().getId());
        vendorPlanSelectionHistorySummaryDto.setSelectedMonth(vendorPlanSelectionHistory.getSelectedMonth());
        vendorPlanSelectionHistorySummaryDto.setRoomNumber(vendorPlanSelectionHistory.getRoomNumber());
        vendorPlanSelectionHistorySummaryDto.setVendorPlanHistorySummaryDto(vendorPlanHistoryMapper.toSummaryDto(vendorPlanSelectionHistory.getPlan()));
        return vendorPlanSelectionHistorySummaryDto;
    }
}
