package com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import java.time.YearMonth;
import java.util.List;

public interface VendorPlanSelectionUserService {
    public VendorPlanSelectionSummaryDto getVendorPlanSelectionById(Long id);
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserId(Long userId);
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByMonth(YearMonth month);
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByMonthAndVendorId(YearMonth month, Long vendorId);
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserIdAndMonth(Long userId, YearMonth month);
    public VendorPlanSelectionResponseDto createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto, Long vendorPlanId, Long userId);
}
