package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.exception.DuplicateResourceException;
import com.sstinternaltools.sstinternal_tools.mess.exception.ResourceNotFoundException;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanSelectionMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanRepository;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanSelectionRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanSelectionAdminService;
import com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface.VendorPlanSelectionHistoryService;
import com.sstinternaltools.sstinternal_tools.security.exception.InvalidCredentialsException;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VendorPlanSelectionAdminServiceImpl implements VendorPlanSelectionAdminService {
    private final UserRepository userRepository;
    private final VendorPlanRepository vendorPlanRepository;
    private final VendorPlanSelectionRepository vendorPlanSelectionRepository;
    private final VendorPlanSelectionMapper vendorPlanSelectionMapper;
    private final VendorPlanSelectionHistoryService vendorPlanSelectionHistoryService;

    public VendorPlanSelectionAdminServiceImpl(UserRepository userRepository, VendorPlanRepository vendorPlanRepository, VendorPlanSelectionRepository vendorPlanSelectionRepository, VendorPlanSelectionMapper vendorPlanSelectionMapper, VendorPlanSelectionHistoryService vendorPlanSelectionHistoryService) {
        this.userRepository = userRepository;
        this.vendorPlanRepository = vendorPlanRepository;
        this.vendorPlanSelectionRepository = vendorPlanSelectionRepository;
        this.vendorPlanSelectionMapper = vendorPlanSelectionMapper;
        this.vendorPlanSelectionHistoryService = vendorPlanSelectionHistoryService;
    }

    @Override
    public List<VendorPlanSelectionResponseDto> createVendorPlanSelection(List<VendorPlanSelectionCreateDto> vendorPlanSelectionCreateDtos, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        if (vendorPlanSelectionCreateDtos.isEmpty()) {
            throw new InvalidCredentialsException("List of vendor plans cannot be empty.");
        }
        Map<MealType, String> meals = new HashMap<>();
        for (VendorPlanSelectionCreateDto createDto : vendorPlanSelectionCreateDtos) {
            VendorPlan vendorPlan = vendorPlanRepository.findById(createDto.getVendorPlanId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan not found with plan id: " + createDto.getVendorPlanId()));
            for (MealType meal : vendorPlan.getMealTypes()) {
                if (meals.containsKey(meal)) {
                    throw new DuplicateResourceException("Meal type " + meal + " already chosen in vendor plan " + meals.get(meal));
                }
                meals.put(meal, vendorPlan.getPlanName());
            }
        }
        List<VendorPlanSelectionResponseDto> vendorPlanSelectionResponseDtos = new ArrayList<>();
        for (VendorPlanSelectionCreateDto createDto : vendorPlanSelectionCreateDtos) {
            VendorPlan vendorPlan = vendorPlanRepository.findById(createDto.getVendorPlanId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan not found with plan id: " + createDto.getVendorPlanId()));
            VendorPlanSelection vendorPlanSelection = vendorPlanSelectionMapper.fromCreateDto(createDto, vendorPlan, user);
            VendorPlanSelection savedVendorPlanSelection = vendorPlanSelectionRepository.save(vendorPlanSelection);
            vendorPlanSelectionResponseDtos.add(vendorPlanSelectionMapper.toResponseDto(savedVendorPlanSelection));
        }
        vendorPlanSelectionHistoryService.updateSelectionHistoryIfWithinWindow(user);
        return vendorPlanSelectionResponseDtos;
    }

    @Transactional
    @Override
    public List<VendorPlanSelectionResponseDto> updateVendorPlanSelection(List<VendorPlanSelectionUpdateDto> vendorPlanSelectionUpdateDtos, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        Map<MealType, String> meals = new HashMap<>();
        for (VendorPlanSelectionUpdateDto updateDto : vendorPlanSelectionUpdateDtos) {
            VendorPlan vendorPlan = vendorPlanRepository.findById(updateDto.getVendorPlanId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan not found with plan id: " + updateDto.getVendorPlanId()));
            for (MealType meal : vendorPlan.getMealTypes()) {
                if (meals.containsKey(meal)) {
                    throw new DuplicateResourceException("Meal type " + meal + " already chosen in vendor plan " + meals.get(meal));
                }
                meals.put(meal, vendorPlan.getPlanName());
            }
        }
        deleteVendorPlanSelection(userId);
        List<VendorPlanSelectionResponseDto> vendorPlanSelectionResponseDtos = new ArrayList<>();
        for (VendorPlanSelectionUpdateDto updateDto : vendorPlanSelectionUpdateDtos) {
            VendorPlan vendorPlan = vendorPlanRepository.findById(updateDto.getVendorPlanId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan not found with plan id: " + updateDto.getVendorPlanId()));
            VendorPlanSelection vendorPlanSelection = vendorPlanSelectionMapper.fromUpdateDto(updateDto, vendorPlan, user);
            VendorPlanSelection savedVendorPlanSelection = vendorPlanSelectionRepository.save(vendorPlanSelection);
            vendorPlanSelectionResponseDtos.add(vendorPlanSelectionMapper.toResponseDto(savedVendorPlanSelection));
        }
        vendorPlanSelectionHistoryService.updateSelectionHistoryIfWithinWindow(user);
        return vendorPlanSelectionResponseDtos;
    }


    @Transactional
    @Override
    public void deleteVendorPlanSelection(Long userId) {
        if (!vendorPlanSelectionRepository.existsByUserId(userId)) {
            throw new ResourceNotFoundException("No Vendor Plan Selection found for the given user.");
        }
        vendorPlanSelectionRepository.deleteAllByUserId(userId);
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with this userId."));
        vendorPlanSelectionHistoryService.updateSelectionHistoryIfWithinWindow(user);
    }

    @Override
    public void deleteAllVendorPlanSelection() {
        vendorPlanSelectionRepository.deleteAll();
    }
}
