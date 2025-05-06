package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.exception.ResourceNotFoundException;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanSelectionMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanRepository;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanSelectionRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanSelectionAdminService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class VendorPlanSelectionAdminServiceImpl implements VendorPlanSelectionAdminService {
    private final UserRepository userRepository;
    private final VendorPlanRepository vendorPlanRepository;
    private final VendorPlanSelectionRepository vendorPlanSelectionRepository;
    private final VendorPlanSelectionMapper vendorPlanSelectionMapper;

    public VendorPlanSelectionAdminServiceImpl(UserRepository userRepository, VendorPlanRepository vendorPlanRepository, VendorPlanSelectionRepository vendorPlanSelectionRepository, VendorPlanSelectionMapper vendorPlanSelectionMapper) {
        this.userRepository = userRepository;
        this.vendorPlanRepository = vendorPlanRepository;
        this.vendorPlanSelectionRepository = vendorPlanSelectionRepository;
        this.vendorPlanSelectionMapper = vendorPlanSelectionMapper;
    }

    @Override
    public VendorPlanSelectionResponseDto createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto, Long vendorPlanId, Long userId) {
        VendorPlan vendorPlan = vendorPlanRepository.findById(vendorPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        VendorPlanSelection vendorPlanSelection = vendorPlanSelectionMapper.fromCreateDto(vendorPlanSelectionCreateDto, vendorPlan, user);
        VendorPlanSelection savedVendorPlanSelection = vendorPlanSelectionRepository.save(vendorPlanSelection);
        return vendorPlanSelectionMapper.toResponseDto(savedVendorPlanSelection);
    }

    @Override
    public VendorPlanSelectionResponseDto updateVendorPlanSelection(VendorPlanSelectionUpdateDto vendorPlanSelectionUpdateDto, Long vendorPlanSelectionId) {
        VendorPlanSelection vendorPlanSelection = vendorPlanSelectionRepository.findById(vendorPlanSelectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan Selection not found"));
        VendorPlan vendorPlan = vendorPlanRepository.findById(vendorPlanSelectionUpdateDto.getPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan not found"));
        vendorPlanSelection = vendorPlanSelectionMapper.fromUpdateDto(vendorPlanSelectionUpdateDto, vendorPlanSelection, vendorPlan);
        VendorPlanSelection savedVendorPlanSelection = vendorPlanSelectionRepository.save(vendorPlanSelection);
        return vendorPlanSelectionMapper.toResponseDto(savedVendorPlanSelection);
    }

    @Override
    public void deleteVendorPlanSelectionDto(Long id) {
        vendorPlanSelectionRepository.deleteById(id);
    }
}
