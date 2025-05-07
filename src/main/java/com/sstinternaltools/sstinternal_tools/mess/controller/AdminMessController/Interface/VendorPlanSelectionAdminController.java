package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import org.springframework.http.ResponseEntity;

public interface VendorPlanSelectionAdminController {
    ResponseEntity<VendorPlanSelectionResponseDto> createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto);
    ResponseEntity<VendorPlanSelectionResponseDto> updateVendorPlanSelection(Long vendorPlanSelectionId, VendorPlanSelectionUpdateDto vendorPlanSelectionUpdateDto);
    ResponseEntity<String> deleteVendorPlanSelection(Long id);
}
