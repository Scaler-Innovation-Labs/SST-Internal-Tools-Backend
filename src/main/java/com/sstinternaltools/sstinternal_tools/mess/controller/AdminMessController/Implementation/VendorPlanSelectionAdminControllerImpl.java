package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Implementation;


import com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface.VendorPlanSelectionAdminController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanSelectionAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mess/vendorPlanSelection")
public class VendorPlanSelectionAdminControllerImpl implements VendorPlanSelectionAdminController {

    private final VendorPlanSelectionAdminService vendorPlanSelectionAdminService;

    public VendorPlanSelectionAdminControllerImpl(VendorPlanSelectionAdminService vendorPlanSelectionAdminService) {
        this.vendorPlanSelectionAdminService = vendorPlanSelectionAdminService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<VendorPlanSelectionResponseDto> createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto) {
        VendorPlanSelectionResponseDto vendorPlanSelectionResponseDto = vendorPlanSelectionAdminService.createVendorPlanSelection(vendorPlanSelectionCreateDto);
        return ResponseEntity.ok(vendorPlanSelectionResponseDto);
    }

    @Override
    public ResponseEntity<String> deleteVendorPlanSelection(Long id) {
        vendorPlanSelectionAdminService.deleteVendorPlanSelectionDto(id);
        return ResponseEntity.ok("Vendor Plan Selection Deleted");
    }
}
