package com.sstinternaltools.sstinternal_tools.mess.controller.UserMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionHistoryDtos.VendorPlanSelectionHistorySummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorPlanSelectionUserController {
    ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserId(Long userId);
    ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserIdAndVendorId(Long userId, Long vendorId);
    ResponseEntity<List<VendorPlanSelectionResponseDto>> createVendorPlanSelection(List<VendorPlanSelectionCreateDto> vendorPlanSelectionCreateDtos, Long userId);
    ResponseEntity<List<VendorPlanSelectionResponseDto>> updateVendorPlanSelection(List<VendorPlanSelectionUpdateDto> vendorPlanSelectionUpdateDtos, Long userId);
    ResponseEntity<List<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByUserId(Long userId);
    ResponseEntity<List<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByUserIdAndMonth(Long userId, Integer month, Integer year);
}
