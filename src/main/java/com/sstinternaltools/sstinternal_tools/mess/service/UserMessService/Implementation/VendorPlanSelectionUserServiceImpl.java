package com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.exception.ResourceNotFoundException;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanSelectionMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanRepository;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanSelectionRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Interface.VendorPlanSelectionUserService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

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
    public VendorPlanSelectionSummaryDto getVendorPlanSelectionById(Long id) {
        VendorPlanSelection vendorPlanSelection = vendorPlanSelectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan Selection not found"));
        return vendorPlanSelectionMapper.toSummaryDto(vendorPlanSelection);
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
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByMonth(YearMonth month) {
        List<VendorPlanSelection> vendorPlanSelections = vendorPlanSelectionRepository.findBySelectedMonth(month);
        List<VendorPlanSelectionSummaryDto> vendorPlanSelectionSummariesDtos = new ArrayList<>();
        for (VendorPlanSelection vendorPlanSelection : vendorPlanSelections) {
            vendorPlanSelectionSummariesDtos.add(vendorPlanSelectionMapper.toSummaryDto(vendorPlanSelection));
        }
        return vendorPlanSelectionSummariesDtos;
    }

    @Override
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByMonthAndVendorId(YearMonth month, Long vendorId) {
        List<VendorPlanSelection> vendorPlanSelections = vendorPlanSelectionRepository.findBySelectedMonthAndPlanVendorId(month, vendorId);
        List<VendorPlanSelectionSummaryDto> vendorPlanSelectionSummariesDtos = new ArrayList<>();
        for (VendorPlanSelection vendorPlanSelection : vendorPlanSelections) {
            vendorPlanSelectionSummariesDtos.add(vendorPlanSelectionMapper.toSummaryDto(vendorPlanSelection));
        }
        return vendorPlanSelectionSummariesDtos;
    }

    @Override
    public List<VendorPlanSelectionSummaryDto> getVendorPlanSelectionsByUserIdAndMonth(Long userId, YearMonth month) {
        List<VendorPlanSelection> vendorPlanSelections = vendorPlanSelectionRepository.findByUserIdAndSelectedMonth(userId, month);
        List<VendorPlanSelectionSummaryDto> vendorPlanSelectionSummariesDtos = new ArrayList<>();
        for (VendorPlanSelection vendorPlanSelection : vendorPlanSelections) {
            vendorPlanSelectionSummariesDtos.add(vendorPlanSelectionMapper.toSummaryDto(vendorPlanSelection));
        }
        return vendorPlanSelectionSummariesDtos;
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
        VendorPlan vendorPlan = vendorPlanRepository.findById(vendorPlanSelectionUpdateDto.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan not found"));
        vendorPlanSelection = vendorPlanSelectionMapper.fromUpdateDto(vendorPlanSelectionUpdateDto, vendorPlanSelection, vendorPlan);
        VendorPlanSelection savedVendorPlanSelection = vendorPlanSelectionRepository.save(vendorPlanSelection);
        return vendorPlanSelectionMapper.toResponseDto(savedVendorPlanSelection);
    }

    @Override
    public void deleteVendorPlanSelection(Long id) {
        if (!vendorPlanSelectionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vendor Plan Selection not found.");
        }
        vendorPlanSelectionRepository.deleteById(id);
    }
}
