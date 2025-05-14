package com.sstinternaltools.sstinternal_tools.mess.controller.UserMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionSummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import org.springframework.http.ResponseEntity;

import java.time.YearMonth;
import java.util.List;

public interface VendorPlanSelectionUserController {
    ResponseEntity<VendorPlanSelectionSummaryDto> getVendorPlanSelectionById(Long Id);
    ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserId(Long userId);
//    ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByMonth(YearMonth month);
//    ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByMonthAndVendorId(YearMonth month, Long vendorId);
//    ResponseEntity<List<VendorPlanSelectionSummaryDto>> getVendorPlanSelectionsByUserIdAndMonth(Long userId, YearMonth month);
    ResponseEntity<VendorPlanSelectionResponseDto> createVendorPlanSelection(VendorPlanSelectionCreateDto vendorPlanSelectionCreateDto);
    ResponseEntity<VendorPlanSelectionResponseDto> updateVendorPlanSelection(VendorPlanSelectionUpdateDto vendorPlanSelectionUpdateDto, Long vendorPlanSelectionId);
    ResponseEntity<String> deleteVendorPlanSelection(Long id);
}
