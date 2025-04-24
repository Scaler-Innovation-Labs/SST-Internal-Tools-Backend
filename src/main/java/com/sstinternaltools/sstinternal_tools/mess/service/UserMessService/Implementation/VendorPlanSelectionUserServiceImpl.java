package com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanSelectionMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanSelectionRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Interface.VendorPlanSelectionUserService;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendorPlanSelectionUserServiceImpl implements VendorPlanSelectionUserService {

    private final VendorPlanSelectionRepository vendorPlanSelectionRepository;
    private final VendorPlanSelectionMapper vendorPlanSelectionMapper;

    public VendorPlanSelectionUserServiceImpl(VendorPlanSelectionRepository vendorPlanSelectionRepository, VendorPlanSelectionMapper vendorPlanSelectionMapper) {
        this.vendorPlanSelectionRepository = vendorPlanSelectionRepository;
        this.vendorPlanSelectionMapper = vendorPlanSelectionMapper;
    }

    @Override
    public VendorPlanSelectionSummaryDto getVendorPlanSelectionById(Long id) {
        VendorPlanSelection vendorPlanSelection = vendorPlanSelectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan Selection Not Found"));
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
    public VendorPlanSelectionResponseDto createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto) {
        return null;
    }
}
