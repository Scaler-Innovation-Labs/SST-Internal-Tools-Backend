package com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces;

public interface VendorDtoMapper <Vendor,VendorCreateDto, VendorUpdateDto, VendorResponseDto,  VendorSummaryDto>{
    Vendor fromCreateDto(VendorCreateDto createDto);
    Vendor fromUpdateDto(VendorUpdateDto updateDto,Vendor entity);
    VendorResponseDto toResponseDto(Vendor vendor);
    VendorSummaryDto toSummaryDto(Vendor vendor);
}
