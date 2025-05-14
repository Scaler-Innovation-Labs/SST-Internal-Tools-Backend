package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface.VendorPlanAdminController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanDtos.VendorPlanUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanAdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mess/admin/vendorPlan")
public class VendorPlanAdminControllerImpl implements VendorPlanAdminController {

    private final VendorPlanAdminService vendorPlanAdminService;

    public VendorPlanAdminControllerImpl(VendorPlanAdminService vendorPlanAdminService) {
        this.vendorPlanAdminService = vendorPlanAdminService;
    }

    @Override
    @GetMapping("/fetch/{id}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<VendorPlanSummaryDto> getVendorPlanById(@PathVariable Long id) {
        VendorPlanSummaryDto vendorPlanSummaryDto = vendorPlanAdminService.getVendorPlanById(id);
        return ResponseEntity.ok(vendorPlanSummaryDto);
    }

    @Override
    @GetMapping("/fetchAll")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<List<VendorPlanSummaryDto>> getAllVendorPlans() {
        List<VendorPlanSummaryDto> vendorPlanSummaryDtos = vendorPlanAdminService.getAllVendorPlans();
        return ResponseEntity.ok(vendorPlanSummaryDtos);
    }

    @Override
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<VendorPlanResponseDto> createVendorPlan(@Valid @RequestBody VendorPlanCreateDto vendorPlanCreateDto) {
        VendorPlanResponseDto vendorPlanResponseDto = vendorPlanAdminService.createVendorPlan(vendorPlanCreateDto, vendorPlanCreateDto.getVendorId());
        return ResponseEntity.ok(vendorPlanResponseDto);
    }

    @Override
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<VendorPlanResponseDto> updateVendorPlan(@Valid @RequestBody VendorPlanUpdateDto vendorPlanUpdateDto, @PathVariable Long id) {
        VendorPlanResponseDto vendorPlanResponseDto = vendorPlanAdminService.updateVendorPlan(vendorPlanUpdateDto, id);
        return ResponseEntity.ok(vendorPlanResponseDto);
    }

    @Override
    @PatchMapping("/partialUpdate/{id}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<VendorPlanResponseDto> partialUpdateVendorPlan(@Valid @RequestBody VendorPlanUpdateDto vendorPlanUpdateDto, @PathVariable Long id) {
        VendorPlanResponseDto vendorPlanResponseDto = vendorPlanAdminService.partialUpdateVendorPlan(vendorPlanUpdateDto, id);
        return ResponseEntity.ok(vendorPlanResponseDto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<String> deleteVendorPlan(@PathVariable Long id) {
        vendorPlanAdminService.deleteVendorPlan(id);
        return ResponseEntity.ok("Vendor plan deleted successfully");
    }
}
