package com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VendorAdminService {

    VendorSummaryDto getVendorById(Long id);
    List<VendorSummaryDto> getAllVendors();
    VendorResponseDto createVendor(VendorCreateDto vendorCreateDto);
    VendorResponseDto updateVendor(VendorUpdateDto vendorUpdateDto, Long id);
    void deleteVendor(Long id);

}
