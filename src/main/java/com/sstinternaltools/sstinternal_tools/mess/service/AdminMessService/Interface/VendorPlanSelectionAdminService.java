package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;

public interface VendorPlanSelectionAdminService {
    VendorPlanSelectionResponseDto createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto, Long vendorPlanId, Long userId);
    void deleteVendorPlanSelectionDto(Long id);
}
