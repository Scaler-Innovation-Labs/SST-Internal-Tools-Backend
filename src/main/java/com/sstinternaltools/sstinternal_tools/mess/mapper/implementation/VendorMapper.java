package com.sstinternaltools.sstinternal_tools.mess.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.Vendor;
import com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces.DtoMapper;

public class VendorMapper implements DtoMapper<Vendor, VendorCreateDto, VendorUpdateDto, VendorResponseDto,VendorSummaryDto> {
    @Override
    public Vendor fromCreateDto(VendorCreateDto createDto) {
        Vendor vendor = new Vendor();
        vendor.setVendorName(createDto.getVendorName());
        return vendor;
    }

    @Override
    public Vendor fromUpdateDto(VendorUpdateDto updateDto, Vendor vendor) {
        vendor.setVendorName(updateDto.getVendorName());
        return vendor;
    }

    @Override
    public VendorResponseDto toResponseDto(Vendor vendor) {
        VendorResponseDto vendorResponseDto=new VendorResponseDto();
        vendorResponseDto.setVendorId(vendor.getId());
        vendorResponseDto.setVendorName(vendor.getVendorName());
        return vendorResponseDto;
    }

    @Override
    public VendorSummaryDto toSummaryDto(Vendor vendor) {
        VendorSummaryDto vendorSummaryDto=new VendorSummaryDto();
        vendorSummaryDto.setVendorName(vendor.getVendorName());
        return vendorSummaryDto;
    }
}
