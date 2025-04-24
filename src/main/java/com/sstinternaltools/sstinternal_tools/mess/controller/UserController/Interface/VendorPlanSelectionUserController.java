package com.sstinternaltools.sstinternal_tools.mess.controller.UserController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import org.springframework.http.ResponseEntity;

import java.time.YearMonth;
import java.util.List;


public interface VendorPlanSelectionUserController {
    public ResponseEntity<VendorPlanSelectionSummaryDto> getVendorPlanSelectionById(Long Id);
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserId(Long userId);
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByMonth(YearMonth month);
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByMonthAndVendorId(YearMonth month, Long vendorId);
    public ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserIdAndMonth(Long userId, YearMonth month);
    public ResponseEntity<String> createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto);
}
