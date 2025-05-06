package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;

public interface VendorPlanSelectionAdminService {
    VendorPlanSelectionResponseDto createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto, Long vendorPlanId, Long userId);
    VendorPlanSelectionResponseDto updateVendorPlanSelection(VendorPlanSelectionUpdateDto vendorPlanSelectionUpdateDto, Long vendorPlanSelectionId);
    void deleteVendorPlanSelectionDto(Long id);
}
