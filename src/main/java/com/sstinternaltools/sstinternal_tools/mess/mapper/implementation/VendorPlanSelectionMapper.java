package com.sstinternaltools.sstinternal_tools.mess.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces.VendorPlanSelectionDtoMapper;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class VendorPlanSelectionMapper implements VendorPlanSelectionDtoMapper <VendorPlanSelection, VendorPlan,User, VendorPlanSelectionCreateDto, VendorPlanSelectionUpdateDto, VendorPlanSelectionResponseDto, VendorPlanSelectionSummaryDto>{

    @Override
    public VendorPlanSelection fromCreateDto(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto, VendorPlan vendorPlan, User user) {
        VendorPlanSelection vendorPlanSelection = new VendorPlanSelection();
        vendorPlanSelection.setPlan(vendorPlan);
        vendorPlanSelection.setUser(user);
        vendorPlanSelection.setMonth(vendorPlanSelectionCreateDto.getSelectedMonth());
        vendorPlanSelection.setRoomNumber(vendorPlanSelectionCreateDto.getRoomNumber());
        vendorPlanSelection.setHostel(vendorPlanSelectionCreateDto.getHostel());
        return vendorPlanSelection;
    }

    @Override
    public VendorPlanSelection fromUpdateDto(VendorPlanSelectionUpdateDto vendorPlanSelectionUpdateDto, VendorPlan vendorPlan, User user) {
        VendorPlanSelection vendorPlanSelection = new VendorPlanSelection();
        vendorPlanSelection.setPlan(vendorPlan);
        vendorPlanSelection.setUser(user);
        vendorPlanSelection.setMonth(vendorPlanSelectionUpdateDto.getSelectedMonth());
        vendorPlanSelection.setRoomNumber(vendorPlanSelectionUpdateDto.getRoomNumber());
        vendorPlanSelection.setHostel(vendorPlanSelectionUpdateDto.getHostel());
        return vendorPlanSelection;
    }

    @Override
    public VendorPlanSelectionResponseDto toResponseDto(VendorPlanSelection vendorPlanSelection) {
        VendorPlanSelectionResponseDto vendorPlanSelectionResponseDto = new VendorPlanSelectionResponseDto();
        vendorPlanSelectionResponseDto.setVendorPlanSelectionId(vendorPlanSelection.getId());
        vendorPlanSelectionResponseDto.setVendorPlanId(vendorPlanSelection.getPlan().getId());
        vendorPlanSelectionResponseDto.setSelectedMonth(vendorPlanSelection.getMonth());
        vendorPlanSelectionResponseDto.setUserId(vendorPlanSelection.getUser().getId());
        vendorPlanSelectionResponseDto.setRoomNumber(vendorPlanSelection.getRoomNumber());
        vendorPlanSelectionResponseDto.setHostel(vendorPlanSelection.getHostel());
        return vendorPlanSelectionResponseDto;
    }

    @Override
    public VendorPlanSelectionSummaryDto toSummaryDto(VendorPlanSelection vendorPlanSelection) {
        VendorPlanSelectionSummaryDto vendorPlanSelectionSummaryDto = new VendorPlanSelectionSummaryDto();
        vendorPlanSelectionSummaryDto.setVendorPlanName(vendorPlanSelection.getPlan().getPlanName());
        vendorPlanSelectionSummaryDto.setSelectedMonth(vendorPlanSelection.getMonth());
        vendorPlanSelectionSummaryDto.setVendorName(vendorPlanSelection.getPlan().getVendor().getVendorName());
        vendorPlanSelectionSummaryDto.setMealTypes(vendorPlanSelection.getPlan().getMealTypes());
        vendorPlanSelectionSummaryDto.setFee(vendorPlanSelection.getPlan().getFee());
        vendorPlanSelectionSummaryDto.setRoomNumber(vendorPlanSelection.getRoomNumber());
        vendorPlanSelectionSummaryDto.setHostel(vendorPlanSelection.getHostel());
        return vendorPlanSelectionSummaryDto;
    }
}
