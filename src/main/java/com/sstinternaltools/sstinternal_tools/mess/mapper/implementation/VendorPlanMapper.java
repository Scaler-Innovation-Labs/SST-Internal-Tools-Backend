package com.sstinternaltools.sstinternal_tools.mess.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.Vendor;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces.VendorPlanDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class VendorPlanMapper implements VendorPlanDtoMapper<VendorPlan, Vendor, VendorPlanCreateDto, VendorPlanUpdateDto, VendorPlanResponseDto, VendorPlanSummaryDto> {
    @Override
    public VendorPlan fromCreateDto(VendorPlanCreateDto vendorPlanCreateDto,Vendor vendor) {
        VendorPlan vendorPlan = new VendorPlan();
        vendorPlan.setVendor(vendor);
        vendorPlan.setPlanName(vendorPlanCreateDto.getPlanName());
        vendorPlan.setFee(vendorPlanCreateDto.getFee());
        vendorPlan.setMealTypes(vendorPlanCreateDto.getMealTypes());
        return vendorPlan;
    }

    @Override
    public VendorPlan fromUpdateDto(VendorPlanUpdateDto vendorPlanUpdateDto, VendorPlan vendorPlan) {

        if (vendorPlanUpdateDto.getFee() != null) {
            vendorPlan.setFee(vendorPlanUpdateDto.getFee());
        }
        if (vendorPlanUpdateDto.getMealTypes() != null) {
            vendorPlan.setMealTypes(vendorPlanUpdateDto.getMealTypes());
        }
        if (vendorPlanUpdateDto.getPlanName() != null) {
            vendorPlan.setPlanName(vendorPlanUpdateDto.getPlanName());
        }
        return vendorPlan;
    }

    @Override
    public VendorPlanResponseDto toResponseDto(VendorPlan vendorPlan) {
        VendorPlanResponseDto vendorPlanResponseDto = new VendorPlanResponseDto();
        vendorPlanResponseDto.setVendorPlanId(vendorPlan.getId());
        vendorPlanResponseDto.setPlanName(vendorPlan.getPlanName());
        vendorPlanResponseDto.setFee(vendorPlan.getFee());
        vendorPlanResponseDto.setMealTypes(vendorPlan.getMealTypes());
        vendorPlanResponseDto.setVendorId(vendorPlan.getVendor().getId());
        return vendorPlanResponseDto;
    }

    @Override
    public VendorPlanSummaryDto toSummaryDto(VendorPlan vendorPlan) {
        VendorPlanSummaryDto vendorPlanSummaryDto = new VendorPlanSummaryDto();
        vendorPlanSummaryDto.setFee(vendorPlan.getFee());
        vendorPlanSummaryDto.setMealTypes(vendorPlan.getMealTypes());
        vendorPlanSummaryDto.setPlanName(vendorPlan.getPlanName());
        vendorPlanSummaryDto.setVendorName(vendorPlan.getVendor().getVendorName());
        return vendorPlanSummaryDto;
    }
}
