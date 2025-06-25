package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Implementation;


import com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface.VendorPlanSelectionAdminController;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionHistoryDtos.VendorPlanSelectionHistorySummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.service.AdminMessService.Interface.VendorPlanSelectionAdminService;
import com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface.VendorPlanSelectionHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT_ADMIN', 'SUPER_ADMIN')")
@RequestMapping("/mess/admin/vendorPlanSelection")
public class VendorPlanSelectionAdminControllerImpl implements VendorPlanSelectionAdminController {

    private final VendorPlanSelectionAdminService vendorPlanSelectionAdminService;
    private final VendorPlanSelectionHistoryService vendorPlanSelectionHistoryService;

    public VendorPlanSelectionAdminControllerImpl(VendorPlanSelectionAdminService vendorPlanSelectionAdminService, VendorPlanSelectionHistoryService vendorPlanSelectionHistoryService) {
        this.vendorPlanSelectionAdminService = vendorPlanSelectionAdminService;
        this.vendorPlanSelectionHistoryService = vendorPlanSelectionHistoryService;
    }

    @Override
    @PostMapping("/create/{userId}")
    public ResponseEntity<List<VendorPlanSelectionResponseDto>> createVendorPlanSelection(@RequestBody List<VendorPlanSelectionCreateDto> vendorPlanSelectionCreateDtos, @PathVariable Long userId) {
        List<VendorPlanSelectionResponseDto> vendorPlanSelectionResponseDtos = vendorPlanSelectionAdminService.createVendorPlanSelection(vendorPlanSelectionCreateDtos, userId);
        return ResponseEntity.ok(vendorPlanSelectionResponseDtos);
    }

    @Override
    @PutMapping("/update/{userId}")
    public ResponseEntity<List<VendorPlanSelectionResponseDto>> updateVendorPlanSelection(@RequestBody List<VendorPlanSelectionUpdateDto> vendorPlanSelectionUpdateDtos, @PathVariable Long userId) {
        List<VendorPlanSelectionResponseDto> vendorPlanSelectionResponseDtos = vendorPlanSelectionAdminService.updateVendorPlanSelection(vendorPlanSelectionUpdateDtos, userId);
        return ResponseEntity.ok(vendorPlanSelectionResponseDtos);
    }

    @Override
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteVendorPlanSelection(@PathVariable Long userId) {
        vendorPlanSelectionAdminService.deleteVendorPlanSelection(userId);
        return ResponseEntity.ok("Vendor Plan Selection Deleted");
    }

    @Override
    @GetMapping("/historyByMonthAndVendorPlanName")
    public ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByMonthAndVendorPlan(@RequestParam Integer month,
                                                                                                                    @RequestParam Integer year,
                                                                                                                    @RequestParam String vendorPlan,
                                                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDate date = LocalDate.of(year, month, 1);
        Page<VendorPlanSelectionHistorySummaryDto> vendorPlanSelectionHistorySummaryDtos = vendorPlanSelectionHistoryService.getVendorPlanSelectionHistoryByMonthAndVendorPlan(date, vendorPlan, pageable);
        return ResponseEntity.ok(vendorPlanSelectionHistorySummaryDtos);
    }

    @Override
    @GetMapping("/historyByMonthAndVendorName")
    public ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByMonthAndVendor(@RequestParam Integer month,
                                                                                                                    @RequestParam Integer year,
                                                                                                                    @RequestParam String vendorName,
                                                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDate date = LocalDate.of(year, month, 1);
        Page<VendorPlanSelectionHistorySummaryDto> vendorPlanSelectionHistorySummaryDtos = vendorPlanSelectionHistoryService.getVendorPlanSelectionHistoryByMonthAndVendor(date, vendorName, pageable);
        return ResponseEntity.ok(vendorPlanSelectionHistorySummaryDtos);
    }

    @Override
    @GetMapping("/historyByMonth")
    public ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByMonth(@RequestParam Integer month,
                                                                                                           @RequestParam Integer year,
                                                                                                           @RequestParam(defaultValue = "0") int page,
                                                                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDate date = LocalDate.of(year, month, 1);
        Page<VendorPlanSelectionHistorySummaryDto> vendorPlanSelectionHistorySummaryDtos = vendorPlanSelectionHistoryService.getVendorPlanSelectionHistoryByMonth(date, pageable);
        return ResponseEntity.ok(vendorPlanSelectionHistorySummaryDtos);
    }

    @Override
    @GetMapping("/history")
    public ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistory(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VendorPlanSelectionHistorySummaryDto> vendorPlanSelectionHistorySummaryDtos = vendorPlanSelectionHistoryService.getVendorPlanSelectionHistory(pageable);
        return ResponseEntity.ok(vendorPlanSelectionHistorySummaryDtos);
    }
}