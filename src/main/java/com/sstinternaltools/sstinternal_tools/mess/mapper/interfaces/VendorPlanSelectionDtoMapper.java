package com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces;

public interface VendorPlanSelectionDtoMapper <VendorPlanSelection, VendorPlan, User, VendorPlanSelectionCreateDto, VendorPlanSelectionUpdateDto, VendorPlanSelectionResponseDto, VendorPlanSelectionSummaryDto>{
    VendorPlanSelection fromCreateDto(VendorPlanSelectionCreateDto createDto, VendorPlan vendorPlan, User user);
    VendorPlanSelection fromUpdateDto(VendorPlanSelectionUpdateDto updateDto, VendorPlan vendorPlan, User user);
    VendorPlanSelectionResponseDto toResponseDto(VendorPlanSelection vendorPlanSelection);
    VendorPlanSelectionSummaryDto toSummaryDto(VendorPlanSelection vendorPlanSelection);
}
