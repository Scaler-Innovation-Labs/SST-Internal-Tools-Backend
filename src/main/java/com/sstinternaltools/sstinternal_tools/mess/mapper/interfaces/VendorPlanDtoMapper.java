package com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces;

public interface VendorPlanDtoMapper <VendorPlan,Vendor,VendorPlanCreateDto, VendorPlanUpdateDto, VendorPlanResponseDto,  VendorPlanSummaryDto>{
    VendorPlan fromCreateDto(VendorPlanCreateDto createDto,Vendor vendor);
    VendorPlan fromUpdateDto(VendorPlanUpdateDto updateDto,VendorPlan vendorPlan);
    VendorPlanResponseDto toResponseDto(VendorPlan vendorPlan);
    VendorPlanSummaryDto toSummaryDto(VendorPlan vendorPlan);
}
