package com.sstinternaltools.sstinternal_tools.mess.controller.UserMessController.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.controller.UserMessController.Interface.VendorPlanSelectionUserController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Interface.VendorPlanSelectionUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/mess/admin/vendorPlanSelection")
public class VendorPlanSelectionUserControllerImpl implements VendorPlanSelectionUserController {

    private final VendorPlanSelectionUserService vendorPlanSelectionUserService;

    public VendorPlanSelectionUserControllerImpl(VendorPlanSelectionUserService vendorPlanSelectionUserService) {
        this.vendorPlanSelectionUserService = vendorPlanSelectionUserService;
    }

    @Override
    @GetMapping("/fetch/{id}")
    public ResponseEntity<VendorPlanSelectionSummaryDto> getVendorPlanSelectionById(@PathVariable Long id) {
        VendorPlanSelectionSummaryDto vendorPlanSelectionSummaryDto = vendorPlanSelectionUserService.getVendorPlanSelectionById(id);
        return ResponseEntity.ok(vendorPlanSelectionSummaryDto);
    }

    @Override
    @GetMapping("/fetchByUser/{userId}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserId(@PathVariable Long userId) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByUserId(userId);
        return ResponseEntity.ok(vendorPlanSelections);
    }

    @Override
    @GetMapping("/fetch/{month}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByMonth(@PathVariable YearMonth month) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByMonth(month);
        return ResponseEntity.ok(vendorPlanSelections);
    }

    @Override
    @GetMapping("/fetchByMonthAndVendor/{month}/{vendorId}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByMonthAndVendorId(@PathVariable YearMonth month, @PathVariable Long vendorId) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByMonthAndVendorId(month, vendorId);
        return ResponseEntity.ok(vendorPlanSelections);
    }

    @Override
    @GetMapping("/fetchByUserAndMonth/{userId}/{month}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserIdAndMonth(@PathVariable Long userId, @PathVariable YearMonth month) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByUserIdAndMonth(userId, month);
        return ResponseEntity.ok(vendorPlanSelections);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> createVendorPlanSelection(@Valid @RequestBody VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto) {
        VendorPlanSelectionResponseDto vendorPlanSelectionResponseDto = vendorPlanSelectionUserService.createVendorPlanSelection(vendorPlanSelectionCreateDto);
        return ResponseEntity.ok("User created successfully!");
    }
}


// Delete tbd ondelete cascade tbd.