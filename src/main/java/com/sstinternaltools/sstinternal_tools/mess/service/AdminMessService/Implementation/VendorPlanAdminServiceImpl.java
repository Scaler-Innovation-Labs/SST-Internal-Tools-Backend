package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.Vendor;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanRepository;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanAdminService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorPlanAdminServiceImpl implements VendorPlanAdminService {
    private final VendorPlanRepository vendorPlanRepository;
    private final VendorPlanMapper vendorPlanMapper;
    private final VendorRepository vendorRepository;

    public VendorPlanAdminServiceImpl(VendorPlanRepository vendorPlanRepository, VendorPlanMapper vendorPlanMapper, VendorRepository vendorRepository) {
        this.vendorPlanRepository = vendorPlanRepository;
        this.vendorPlanMapper = vendorPlanMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorPlanSummaryDto getVendorPlanById(Long id) {
        VendorPlan vendorPlan = vendorPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor Plan not found."));
        return vendorPlanMapper.toSummaryDto(vendorPlan);
    }

    @Override
    public List<VendorPlanSummaryDto> getAllVendorPlans() {
        List<VendorPlan> vendorPlans = vendorPlanRepository.findAll();
        List<VendorPlanSummaryDto> vendorPlansSummaryDtos = new ArrayList<>();
        for (VendorPlan vendorPlan : vendorPlans) {
            vendorPlansSummaryDtos.add(vendorPlanMapper.toSummaryDto(vendorPlan));
        }
        return vendorPlansSummaryDtos;
    }

    @Override
    public VendorPlanResponseDto createVendorPlan(VendorPlanCreateDto vendorPlanCreateDto, Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found."));
        VendorPlan vendorPlan = vendorPlanMapper.fromCreateDto(vendorPlanCreateDto, vendor);
        VendorPlan savedVendorPlan = vendorPlanRepository.save(vendorPlan);
        return vendorPlanMapper.toResponseDto(savedVendorPlan);
    }

    @Override
    public VendorPlanResponseDto updateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id) {
        VendorPlan vendorPlan = vendorPlanRepository.findById(id)
                        .orElseThrow(() -> ResourceNotFoundException());
        if (vendorPlanUpdateDto.getPlanName() == null || vendorPlanUpdateDto.getPlanName().isEmpty()) {
            throw new IllegalArgumentException("Plan name cannot be empty");
        }
        if (vendorPlanUpdateDto.getFee() == null) {
            throw new IllegalArgumentException("Fee cannot be null");
        }
        if (vendorPlanUpdateDto.getFee() < 0) {
            throw new IllegalArgumentException("Fee cannot be negative");
        }
        vendorPlan = vendorPlanMapper.fromUpdateDto(vendorPlanUpdateDto, vendorPlan);
        VendorPlan savedVendorPlan = vendorPlanRepository.save(vendorPlan);
        return vendorPlanMapper.toResponseDto(savedVendorPlan);
    }

    @Override
    public VendorPlanResponseDto partialUpdateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id) {
        VendorPlan vendorPlan = vendorPlanRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException());
        vendorPlan = vendorPlanMapper.fromUpdateDto(vendorPlanUpdateDto, vendorPlan);
        VendorPlan savedVendorPlan = vendorPlanRepository.save(vendorPlan);
        return vendorPlanMapper.toResponseDto(savedVendorPlan);
    }

    @Override
    public void deleteVendorPlan(Long id) {
        vendorPlanRepository.deleteById(id);
    }
}
