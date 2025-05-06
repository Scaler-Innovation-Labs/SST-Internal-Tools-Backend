package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

public interface VendorPlanAdminController {
    public ResponseEntity<VendorPlanSummaryDto> getVendorPlanById(Long id);
    public ResponseEntity<List<VendorPlanSummaryDto>> getAllVendorPlans();
    public ResponseEntity<VendorPlanResponseDto> createVendorPlan(VendorPlanCreateDto vendorPlanCreateDto, Long vendorId);
    public ResponseEntity<VendorPlanResponseDto> updateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id);
    ResponseEntity<VendorPlanResponseDto> partialUpdateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id);
    public ResponseEntity<String> deleteVendorPlan(Long id);
}
