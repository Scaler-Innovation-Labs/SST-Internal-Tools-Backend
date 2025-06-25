package com.sstinternaltools.sstinternal_tools.mess.controller.UserMessController.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.controller.UserMessController.Interface.VendorPlanSelectionUserController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionHistoryDtos.VendorPlanSelectionHistorySummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface.VendorPlanSelectionHistoryService;
import com.sstinternaltools.sstinternal_tools.mess.service.UserMessService.Interface.VendorPlanSelectionUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mess/vendorPlanSelection")
public class VendorPlanSelectionUserControllerImpl implements VendorPlanSelectionUserController {

    private final VendorPlanSelectionUserService vendorPlanSelectionUserService;
    private final VendorPlanSelectionHistoryService vendorPlanSelectionHistoryService;

    public VendorPlanSelectionUserControllerImpl(VendorPlanSelectionUserService vendorPlanSelectionUserService, VendorPlanSelectionHistoryService vendorPlanSelectionHistoryService) {
        this.vendorPlanSelectionUserService = vendorPlanSelectionUserService;
        this.vendorPlanSelectionHistoryService = vendorPlanSelectionHistoryService;
    }

    @Override
    @GetMapping("/fetchByUser/{userId}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserId(@PathVariable Long userId) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByUserId(userId);
        return ResponseEntity.ok(vendorPlanSelections);
    }

    @Override
    @GetMapping("/fetchByUserAndVendor/{userId}/{vendorId}")
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserIdAndVendorId(@PathVariable Long userId, @PathVariable Long vendorId) {
        List<VendorPlanSelectionSummaryDto> vendorPlanSelections = vendorPlanSelectionUserService.getVendorPlanSelectionsByUserIdAndVendorId(userId, vendorId);
        return ResponseEntity.ok(vendorPlanSelections);
    }

    @Override
    @PostMapping("/create/{userId}")
    public ResponseEntity<List<VendorPlanSelectionResponseDto>> createVendorPlanSelection(@RequestBody List<VendorPlanSelectionCreateDto> vendorPlanSelectionCreateDtos, @PathVariable Long userId) {
        List<VendorPlanSelectionResponseDto> vendorPlanSelectionResponseDtos = vendorPlanSelectionUserService.createVendorPlanSelection(vendorPlanSelectionCreateDtos, userId);
        return ResponseEntity.ok(vendorPlanSelectionResponseDtos);
    }

    @Override
    @PutMapping("/update/{userId}")
    public ResponseEntity<List<VendorPlanSelectionResponseDto>> updateVendorPlanSelection(@RequestBody List<VendorPlanSelectionUpdateDto> vendorPlanSelectionUpdateDtos, @PathVariable Long userId) {
        List<VendorPlanSelectionResponseDto> vendorPlanSelectionResponseDtos = vendorPlanSelectionUserService.updateVendorPlanSelection(vendorPlanSelectionUpdateDtos, userId);
        return ResponseEntity.ok(vendorPlanSelectionResponseDtos);
    }

    @Override
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByUserId(@PathVariable Long userId) {
        List<VendorPlanSelectionHistorySummaryDto> vendorPlanSelectionHistory = vendorPlanSelectionHistoryService.getVendorPlanSelectionHistoryByUserId(userId);
        return ResponseEntity.ok(vendorPlanSelectionHistory);
    }

    @Override
    @GetMapping("/historyByMonth/{userId}")
    public ResponseEntity<List<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByUserIdAndMonth(@PathVariable Long userId, @RequestParam Integer month, @RequestParam Integer year) {
        LocalDate date = LocalDate.of(year, month, 1);
        List<VendorPlanSelectionHistorySummaryDto> vendorPlanSelectionHistory = vendorPlanSelectionHistoryService.getVendorPlanSelectionHistoryByUserIdAndMonth(userId, date);
        return ResponseEntity.ok(vendorPlanSelectionHistory);
    }

}