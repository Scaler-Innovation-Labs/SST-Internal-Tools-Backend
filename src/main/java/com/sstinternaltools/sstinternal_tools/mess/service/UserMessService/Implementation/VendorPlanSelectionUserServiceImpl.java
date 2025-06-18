package com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.exception.DuplicateResourceException;
import com.sstinternaltools.sstinternal_tools.mess.exception.ResourceNotFoundException;
import com.sstinternaltools.sstinternal_tools.mess.exception.RestrictedResourceException;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanSelectionMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanRepository;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanSelectionRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Interface.VendorPlanSelectionUserService;
import com.sstinternaltools.sstinternal_tools.security.exception.InvalidCredentialsException;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class VendorPlanSelectionUserServiceImpl implements VendorPlanSelectionUserService {

    private final VendorPlanSelectionRepository vendorPlanSelectionRepository;
    private final VendorPlanSelectionMapper vendorPlanSelectionMapper;
    private final VendorPlanRepository vendorPlanRepository;
    private final UserRepository userRepository;

    public VendorPlanSelectionUserServiceImpl(VendorPlanSelectionRepository vendorPlanSelectionRepository, VendorPlanSelectionMapper vendorPlanSelectionMapper, VendorPlanRepository vendorPlanRepository, UserRepository userRepository) {
        this.vendorPlanSelectionRepository = vendorPlanSelectionRepository;
        this.vendorPlanSelectionMapper = vendorPlanSelectionMapper;
        this.vendorPlanRepository = vendorPlanRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserId(Long userId) {
        List<VendorPlanSelection> vendorPlanSelections = vendorPlanSelectionRepository.findByUserId(userId);
        List<VendorPlanSelectionSummaryDto> vendorPlanSelectionSummariesDtos = new ArrayList<>();
        for (VendorPlanSelection vendorPlanSelection : vendorPlanSelections) {
            vendorPlanSelectionSummariesDtos.add(vendorPlanSelectionMapper.toSummaryDto(vendorPlanSelection));
        }
        return vendorPlanSelectionSummariesDtos;
    }

    @Override
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserIdAndVendorId(Long userId, Long vendorId) {
        List<VendorPlanSelection> vendorPlanSelections = vendorPlanSelectionRepository.findByUserIdAndPlanId(userId, vendorId);
        List<VendorPlanSelectionSummaryDto> vendorPlanSelectionSummariesDtos = new ArrayList<>();
        for (VendorPlanSelection vendorPlanSelection : vendorPlanSelections) {
            vendorPlanSelectionSummariesDtos.add(vendorPlanSelectionMapper.toSummaryDto(vendorPlanSelection));
        }
        return vendorPlanSelectionSummariesDtos;
    }

    @Override
    public List<VendorPlanSelectionResponseDto> createVendorPlanSelection(List<VendorPlanSelectionCreateDto> vendorPlanSelectionCreateDtos, Long userId) {
        validateIfTodayIsWithinAllowedPeriod();
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
        return vendorPlanSelectionResponseDtos;
    }

    @Transactional
    @Override
    public List<VendorPlanSelectionResponseDto> updateVendorPlanSelection(List<VendorPlanSelectionUpdateDto> vendorPlanSelectionUpdateDtos, Long userId) {
        validateIfTodayIsWithinAllowedPeriod();
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
        return vendorPlanSelectionResponseDtos;
    }

    @Transactional
    @Override
    public void deleteVendorPlanSelection(Long userId) {
        validateIfTodayIsWithinAllowedPeriod();
        if (!vendorPlanSelectionRepository.existsByUserId(userId)) {
            throw new ResourceNotFoundException("No Vendor Plan Selection found for the given user.");
        }
        vendorPlanSelectionRepository.deleteAllByUserId(userId);
    }

    private void validateIfTodayIsWithinAllowedPeriod() {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int lastDayOfMonth = today.lengthOfMonth();

        if (day < 25 || day > lastDayOfMonth) {
            throw new RestrictedResourceException("Plan selection can only be done from the 25th to the end of the month.");
        }
    }
}
