package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import org.springframework.http.ResponseEntity;

public interface VendorPlanSelectionAdminController {
    public ResponseEntity<VendorPlanSelectionResponseDto> createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto);
    public ResponseEntity<String> deleteVendorPlanSelection(Long id);
}
