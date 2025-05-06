package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import org.springframework.http.ResponseEntity;

public interface VendorPlanSelectionAdminController {
    public ResponseEntity<VendorPlanSelectionResponseDto> createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto, Long vendorPlanId, Long userId);
    public ResponseEntity<VendorPlanSelectionResponseDto> updateVendorPlanSelection(VendorPlanSelectionUpdateDto vendorPlanSelectionUpdateDto, Long vendorPlanSelectionId);
    public ResponseEntity<String> deleteVendorPlanSelection(Long id);
}
