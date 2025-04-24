package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorAdminController {
    public ResponseEntity<VendorSummaryDto> getVendorById(Long id);
    public ResponseEntity<List<VendorSummaryDto>> getAllVendors();
    public ResponseEntity<VendorResponseDto> createVendor(VendorCreateDto vendorCreateDto);
    public ResponseEntity<VendorResponseDto> updateVendor(VendorUpdateDto vendorUpdateDto);
    public ResponseEntity<String> deleteVendor(Long id);
}
