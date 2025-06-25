package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface.VendorAdminController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos.VendorUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorAdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT_ADMIN', 'SUPER_ADMIN')")
@RequestMapping("/mess/admin/vendor")
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
    @GetMapping("/fetchAll")
    public ResponseEntity<List<VendorSummaryDto>> getAllVendors() {
        List<VendorSummaryDto> vendorSummaryDtos = vendorAdminService.getAllVendors();
        return ResponseEntity.ok(vendorSummaryDtos);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<VendorResponseDto> createVendor(@Valid @RequestBody VendorCreateDto vendorCreateDto) {
        VendorResponseDto vendorResponseDto = vendorAdminService.createVendor(vendorCreateDto);
        return ResponseEntity.ok(vendorResponseDto);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<VendorResponseDto> updateVendor(@Valid @RequestBody VendorUpdateDto vendorUpdateDto, @PathVariable Long id) {
        VendorResponseDto vendorResponseDto = vendorAdminService.updateVendor(vendorUpdateDto, id);
        return ResponseEntity.ok(vendorResponseDto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        vendorAdminService.deleteVendor(id);
        return ResponseEntity.ok("Deleted vendor successfully");
    }
}
