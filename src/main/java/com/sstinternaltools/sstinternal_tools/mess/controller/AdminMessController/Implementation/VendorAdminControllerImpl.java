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
@RequestMapping("/mess/admin/vendor")
public class VendorAdminControllerImpl implements VendorAdminController {

    private final VendorAdminService vendorAdminService;

    public VendorAdminControllerImpl(VendorAdminService vendorAdminService) {
        this.vendorAdminService = vendorAdminService;
    }

    @Override
    @GetMapping("/fetch/{id}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<VendorSummaryDto> getVendorById(@PathVariable Long id) {
        VendorSummaryDto vendorSummaryDto = vendorAdminService.getVendorById(id);
        return ResponseEntity.ok(vendorSummaryDto);
    }

    @Override
    @GetMapping("/fetchAll")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<List<VendorSummaryDto>> getAllVendors() {
        List<VendorSummaryDto> vendorSummaryDtos = vendorAdminService.getAllVendors();
        return ResponseEntity.ok(vendorSummaryDtos);
    }

    @Override
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<VendorResponseDto> createVendor(@Valid @RequestBody VendorCreateDto vendorCreateDto) {
        VendorResponseDto vendorResponseDto = vendorAdminService.createVendor(vendorCreateDto);
        return ResponseEntity.ok(vendorResponseDto);
    }

    @Override
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<VendorResponseDto> updateVendor(@Valid @RequestBody VendorUpdateDto vendorUpdateDto, @PathVariable Long id) {
        VendorResponseDto vendorResponseDto = vendorAdminService.updateVendor(vendorUpdateDto, id);
        return ResponseEntity.ok(vendorResponseDto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        vendorAdminService.deleteVendor(id);
        return ResponseEntity.ok("Deleted vendor successfully");
    }
}
