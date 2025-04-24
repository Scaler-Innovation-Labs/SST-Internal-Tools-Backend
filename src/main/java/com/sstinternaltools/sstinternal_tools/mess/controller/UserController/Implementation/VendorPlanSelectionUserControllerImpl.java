package com.sstinternaltools.sstinternal_tools.mess.controller.UserController.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.controller.UserController.Interface.VendorPlanSelectionUserController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.service.UserService.Interface.VendorPlanSelectionUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/mess/admin/vendor_plan_selection")
public class VendorPlanSelectionUserControllerImpl implements VendorPlanSelectionUserController {

    private final VendorPlanSelectionUserService vendorPlanSelectionUserService;

    public VendorPlanSelectionUserControllerImpl(VendorPlanSelectionUserService vendorPlanSelectionUserService) {
        this.vendorPlanSelectionUserService = vendorPlanSelectionUserService;
    }

    @Override
    @GetMapping("/fetch/{id}")
    public ResponseEntity<VendorPlanSelectionSummaryDto> getVendorPlanSelectionById(@PathVariable Long id) {
        VendorPlanSelectionSummaryDto vendorPlanSelectionSummaryDto = vendorPlanSelectionUserService.getVendorPlanSelectionById(id);
        return null;
    }

    @Override
    @GetMapping("/fetch_by_user/{userId}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserId(@PathVariable Long userId) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByUserId(userId);
        return null;
    }

    @Override
    @GetMapping("/fetch/{month}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByMonth(@PathVariable YearMonth month) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByMonth(month);
        return null;
    }

    @Override
    @GetMapping("/fetch_by_month_and_vendor/{month}/{vendorId}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByMonthAndVendorId(@PathVariable YearMonth month, @PathVariable Long vendorId) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByMonthAndVendorId(month, vendorId);
        return null;
    }

    @Override
    @GetMapping("/fetch_by_user_and_month/{userId}/{month}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserIdAndMonth(@PathVariable Long userId, @PathVariable YearMonth month) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByUserIdAndMonth(userId, month);
        return null;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> createVendorPlanSelection(@Valid @RequestBody VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto) {
        VendorPlanSelectionResponseDto vendorPlanSelectionResponseDto = vendorPlanSelectionUserService.createVendorPlanSelection(vendorPlanSelectionCreateDto);
        return null;
    }
}
