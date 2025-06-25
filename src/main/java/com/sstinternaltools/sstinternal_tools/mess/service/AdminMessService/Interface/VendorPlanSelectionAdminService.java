package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;

import java.util.List;

public interface VendorPlanSelectionAdminService {
    List<VendorPlanSelectionResponseDto> createVendorPlanSelection(List<VendorPlanSelectionCreateDto> vendorPlanSelectionCreateDtos, Long userId);
    List<VendorPlanSelectionResponseDto> updateVendorPlanSelection(List<VendorPlanSelectionUpdateDto> vendorPlanSelectionUpdateDtos, Long userId);
    void deleteVendorPlanSelection(Long userId);
    void deleteAllVendorPlanSelection();
}
