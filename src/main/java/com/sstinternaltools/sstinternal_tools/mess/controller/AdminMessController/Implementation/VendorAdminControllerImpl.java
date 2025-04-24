package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface.VendorAdminController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.Vendor;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mess/vendor")
public class VendorAdminControllerImpl implements VendorAdminController {

    private final VendorAdminService vendorAdminService;

    public VendorAdminControllerImpl(VendorAdminService vendorAdminService) {
        this.vendorAdminService = vendorAdminService;
    }

    @Override
    @GetMapping("/fetch/{id}")
    public ResponseEntity<VendorSummaryDto> getVendorById(@PathVariable Long id) {
        VendorSummaryDto vendorSummaryDto = vendorAdminService.getVendorById(id);
        return ResponseEntity.ok(vendorSummaryDto);
    }

    @Override
    @GetMapping("/fetch_all")
    public ResponseEntity<List<VendorSummaryDto>> getAllVendors() {
        List<VendorSummaryDto> vendorSummaryDtos = vendorAdminService.getAllVendors();
        return ResponseEntity.ok(vendorSummaryDtos);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<VendorResponseDto> createVendor(@RequestBody VendorCreateDto vendorCreateDto) {
        VendorResponseDto vendorResponseDto = vendorAdminService.createVendor(vendorCreateDto);
        return ResponseEntity.ok(vendorResponseDto);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<VendorResponseDto> updateVendor(@RequestBody VendorUpdateDto vendorUpdateDto) {
        VendorResponseDto vendorResponseDto = vendorAdminService.updateVendor(vendorUpdateDto);
        return ResponseEntity.ok(vendorResponseDto);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        vendorAdminService.deleteVendor(id);
        return ResponseEntity.ok("Deleted vendor successfully");
    }
}
