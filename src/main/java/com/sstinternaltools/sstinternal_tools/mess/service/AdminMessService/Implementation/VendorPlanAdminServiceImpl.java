package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanAdminService;

import java.util.ArrayList;
import java.util.List;

public class VendorPlanAdminServiceImpl implements VendorPlanAdminService {
    private final VendorPlanRepository vendorPlanRepository;
    private final VendorPlanMapper vendorPlanMapper;

    public VendorPlanAdminServiceImpl(VendorPlanRepository vendorPlanRepository, VendorPlanMapper vendorPlanMapper) {
        this.vendorPlanRepository = vendorPlanRepository;
        this.vendorPlanMapper = vendorPlanMapper;
    }

    @Override
    public VendorPlanSummaryDto getVendorPlanById(Long id) {
        VendorPlan vendorPlan = vendorPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
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
    public VendorPlanResponseDto createVendorPlan(VendorPlanCreateDto vendorPlanCreateDto) {
//        VendorPlan vendorPlan = new VendorPlan();
//        vendorPlanMapper.fromCreateDto(vendorPlanCreateDto, vendorPlan);
//        Vendor savedVendor = vendorPlanRepository.save(vendorPlan);
        // Let vendorPlan create dto take in  a vendorname from drop down. based on that ill search by name and create vendorplan from that.
        // Then save that vendorPlan and return response dto. If not we should request vendor also in http request body which sounds like a hassle.
        return null;
    }

    @Override
    public VendorPlanResponseDto updateVendorPlan(VendorPlanUpdateDto vendorPlanUpdateDto, Long id) {
        VendorPlan vendorPlan = vendorPlanRepository.findById(id)
                        .orElseThrow(() -> ResourceNotFoundException());
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
