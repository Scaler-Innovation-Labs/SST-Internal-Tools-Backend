package com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces;

public interface VendorPlanSelectionDtoMapper <VendorPlanSelection, Vendor, VendorPlanSelectionCreateDto, VendorPlanSelectionUpdateDto, VendorPlanSelectionResponseDto, VendorPlanSelectionSummaryDto>{
    VendorPlanSelection fromCreateDto(VendorPlanSelectionCreateDto createDto,Vendor vendor);
    VendorPlanSelection fromUpdateDto(VendorPlanSelectionUpdateDto updateDto,VendorPlanSelection vendorPlanSelection);
    VendorPlanSelectionResponseDto toResponseDto(VendorPlanSelection vendorPlanSelection);
    VendorPlanSelectionSummaryDto toSummaryDto(VendorPlanSelection vendorPlanSelection);
}
