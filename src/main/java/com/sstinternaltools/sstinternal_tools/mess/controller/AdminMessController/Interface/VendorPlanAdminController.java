package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorPlanAdminController {
    ResponseEntity<VendorPlanSummaryDto> getVendorPlanById(Long id);
    ResponseEntity<List<VendorPlanSummaryDto>> getAllVendorPlans();
    ResponseEntity<VendorPlanResponseDto> createVendorPlan(VendorPlanCreateDto vendorPlanCreateDto);
    ResponseEntity<VendorPlanResponseDto> updateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id);
    ResponseEntity<VendorPlanResponseDto> partialUpdateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id);
    ResponseEntity<String> deleteVendorPlan(Long id);
}
