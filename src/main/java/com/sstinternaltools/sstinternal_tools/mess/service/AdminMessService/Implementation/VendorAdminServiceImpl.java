package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.Vendor;
import com.sstinternaltools.sstinternal_tools.mess.exception.DuplicateResourceException;
import com.sstinternaltools.sstinternal_tools.mess.exception.ResourceNotFoundException;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorAdminService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorAdminServiceImpl implements VendorAdminService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorAdminServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorSummaryDto getVendorById(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found."));
        return vendorMapper.toSummaryDto(vendor);
    }

    @Override
    public List<VendorSummaryDto> getAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        List<VendorSummaryDto> vendorDtos = new ArrayList<>();
        for (Vendor vendor : vendors) {
            vendorDtos.add(vendorMapper.toSummaryDto(vendor));
        }
        return vendorDtos;
    }

    @Override
    public VendorResponseDto createVendor(VendorCreateDto vendorCreateDto) {
        if (vendorRepository.existsByVendorName(vendorCreateDto.getVendorName())) {
            throw new DuplicateResourceException("Vendor already exists.");
        }
        Vendor vendor = vendorMapper.fromCreateDto(vendorCreateDto);
        Vendor savedVendor = vendorRepository.save(vendor);
        return vendorMapper.toResponseDto(savedVendor);
    }

    @Override
    public VendorResponseDto updateVendor(VendorUpdateDto vendorUpdateDto, Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found."));
        vendor = vendorMapper.fromUpdateDto(vendorUpdateDto, vendor);
        Vendor savedVendor = vendorRepository.save(vendor);
        return vendorMapper.toResponseDto(savedVendor);
    }

    @Override
    public void deleteVendor(Long id) {
        if (!vendorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vendor not found.");
        }
        vendorRepository.deleteById(id);
    }
}
