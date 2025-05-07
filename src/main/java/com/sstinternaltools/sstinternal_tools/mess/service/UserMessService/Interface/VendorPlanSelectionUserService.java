package com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;

import java.time.YearMonth;
import java.util.List;

public interface VendorPlanSelectionUserService {
    VendorPlanSelectionSummaryDto getVendorPlanSelectionById(Long id);
    List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserId(Long userId);
    List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByMonth(YearMonth month);
    List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByMonthAndVendorId(YearMonth month, Long vendorId);
    List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserIdAndMonth(Long userId, YearMonth month);
    VendorPlanSelectionResponseDto createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto, Long vendorPlanId, Long userId);
    VendorPlanSelectionResponseDto updateVendorPlanSelection(VendorPlanSelectionUpdateDto vendorPlanSelectionUpdateDto, Long vendorPlanSelectionId);
    void deleteVendorPlanSelection(Long id);
}
