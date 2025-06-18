package com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;

import java.time.YearMonth;
import java.util.List;

public interface VendorPlanSelectionUserService {
    List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserId(Long userId);
    List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserIdAndVendorId(Long userId, Long vendorId);
    List<VendorPlanSelectionResponseDto> createVendorPlanSelection(List<VendorPlanSelectionCreateDto> vendorPlanSelectionCreateDtos, Long userId);
    List<VendorPlanSelectionResponseDto> updateVendorPlanSelection(List<VendorPlanSelectionUpdateDto> vendorPlanSelectionUpdateDtos, Long userId);
    void deleteVendorPlanSelection(Long userId);
}
