package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanSelectionMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanSelectionRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanSelectionAdminService;
import org.springframework.stereotype.Service;

@Service
public class VendorPlanSelectionAdminServiceImpl implements VendorPlanSelectionAdminService {
    private final VendorPlanSelectionRepository vendorPlanSelectionRepository;
    private final VendorPlanSelectionMapper vendorPlanSelectionMapper;

    public VendorPlanSelectionAdminServiceImpl(VendorPlanSelectionRepository vendorPlanSelectionRepository, VendorPlanSelectionMapper vendorPlanSelectionMapper) {
        this.vendorPlanSelectionRepository = vendorPlanSelectionRepository;
        this.vendorPlanSelectionMapper = vendorPlanSelectionMapper;
    }

    @Override
    public VendorPlanSelectionResponseDto createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto) {
        //VendorPlanSelection vendorPlanSelection = vendorPlanSelectionMapper.fromCreateDto(vendorPlanSelectionCreateDto);
        return null;
    }

    @Override
    public void deleteVendorPlanSelectionDto(Long id) {
        vendorPlanSelectionRepository.deleteById(id);
    }
}
