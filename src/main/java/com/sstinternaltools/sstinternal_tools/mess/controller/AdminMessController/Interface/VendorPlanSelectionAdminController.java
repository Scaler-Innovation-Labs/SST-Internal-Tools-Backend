package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionHistoryDtos.VendorPlanSelectionHistorySummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionCreateDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionResponseDto;
import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionDtos.VendorPlanSelectionUpdateDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.Hostel;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorPlanSelectionAdminController {
    ResponseEntity<List<VendorPlanSelectionResponseDto>> createVendorPlanSelection(List<VendorPlanSelectionCreateDto> vendorPlanSelectionCreateDtos, Long userId);
    ResponseEntity<List<VendorPlanSelectionResponseDto>> updateVendorPlanSelection(List<VendorPlanSelectionUpdateDto> vendorPlanSelectionUpdateDtos, Long userId);
    ResponseEntity<String> deleteVendorPlanSelection(Long userId);
    ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByMonthAndVendorPlan(Integer month, Integer year, String vendorPlan, int page, int size);
    ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByMonthAndVendor(Integer month, Integer year, String vendorName, int page, int size);
    ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByMonth(Integer month, Integer year, int page, int size);
    ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistory(int page, int size);
    ResponseEntity<Page<VendorPlanSelectionHistorySummaryDto>> getVendorPlanSelectionHistoryByHostel(Hostel hostel, int page, int size);
}
