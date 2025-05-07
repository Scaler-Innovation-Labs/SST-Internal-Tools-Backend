package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Implementation;


import com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface.VendorPlanSelectionAdminController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanSelectionAdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mess/admin/vendorPlanSelection")
public class VendorPlanSelectionAdminControllerImpl implements VendorPlanSelectionAdminController {

    private final VendorPlanSelectionAdminService vendorPlanSelectionAdminService;

    public VendorPlanSelectionAdminControllerImpl(VendorPlanSelectionAdminService vendorPlanSelectionAdminService) {
        this.vendorPlanSelectionAdminService = vendorPlanSelectionAdminService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<VendorPlanSelectionResponseDto> createVendorPlanSelection(@Valid @RequestBody VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto) {
        VendorPlanSelectionResponseDto vendorPlanSelectionResponseDto = vendorPlanSelectionAdminService.createVendorPlanSelection(vendorPlanSelectionCreateDto, vendorPlanSelectionCreateDto.getVendorPlanId(), vendorPlanSelectionCreateDto.getUserId());
        return ResponseEntity.ok(vendorPlanSelectionResponseDto);
    }

    @Override
    @PutMapping("/update/{vendorPlanSelectionId}")
    public ResponseEntity<VendorPlanSelectionResponseDto> updateVendorPlanSelection(@Valid @PathVariable Long vendorPlanSelectionId, @Valid @RequestBody VendorPlanSelectionUpdateDto vendorPlanSelectionUpdateDto) {
        VendorPlanSelectionResponseDto vendorPlanSelectionResponseDto = vendorPlanSelectionAdminService.updateVendorPlanSelection(vendorPlanSelectionUpdateDto, vendorPlanSelectionId);
        return ResponseEntity.ok(vendorPlanSelectionResponseDto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendorPlanSelection(@Valid @PathVariable Long id) {
        vendorPlanSelectionAdminService.deleteVendorPlanSelectionDto(id);
        return ResponseEntity.ok("Vendor Plan Selection Deleted");
    }
}
