package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanUpdateDto;

import java.util.List;

public interface VendorPlanAdminService {
    VendorPlanSummaryDto getVendorPlanById(Long id);
    List<VendorPlanSummaryDto> getAllVendorPlans();
    VendorPlanResponseDto createVendorPlan(VendorPlanCreateDto vendorPlanCreateDto, Long vendorId);
    VendorPlanResponseDto updateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id);
    VendorPlanResponseDto partialUpdateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id);
    void deleteVendorPlan(Long id);
}
