package com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.dto.vendorPlanSelectionHistoryDtos.VendorPlanSelectionHistorySummaryDto;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanHistory;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelectionHistory;
import com.sstinternaltools.sstinternal_tools.mess.mapper.implementation.VendorPlanSelectionHistoryMapper;
import com.sstinternaltools.sstinternal_tools.mess.repository.*;
import com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface.VendorPlanSelectionHistoryService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class VendorPlanSelectionHistoryServiceImpl implements VendorPlanSelectionHistoryService {

    private final VendorPlanRepository vendorPlanRepository;
    private final VendorPlanSelectionRepository vendorPlanSelectionRepository;
    private final VendorPlanHistoryRepository vendorPlanHistoryRepository;
    private final VendorPlanSelectionHistoryRepository vendorPlanSelectionHistoryRepository;
    private final VendorPlanSelectionHistoryMapper vendorPlanSelectionHistoryMapper;

    public VendorPlanSelectionHistoryServiceImpl(VendorPlanRepository vendorPlanRepository, VendorPlanSelectionRepository vendorPlanSelectionRepository, VendorPlanHistoryRepository vendorPlanHistoryRepository, VendorPlanSelectionHistoryRepository vendorPlanSelectionHistoryRepository, VendorPlanSelectionHistoryMapper vendorPlanSelectionHistoryMapper) {
        this.vendorPlanRepository = vendorPlanRepository;
        this.vendorPlanSelectionRepository = vendorPlanSelectionRepository;
        this.vendorPlanHistoryRepository = vendorPlanHistoryRepository;
        this.vendorPlanSelectionHistoryRepository = vendorPlanSelectionHistoryRepository;
        this.vendorPlanSelectionHistoryMapper = vendorPlanSelectionHistoryMapper;
    }

    @Override
    public void createHistoryForMonth(LocalDate month) {
        createPlanHistoryHelper();
        List<VendorPlanSelection> selections = vendorPlanSelectionRepository.findAll();
        createHistoryHelper(month, selections);
    }

    @Override
    public void updateSelectionHistoryIfWithinWindow(User user) {
        LocalDate today = LocalDate.now();
        if (today.getDayOfMonth() >= 3 && today.getDayOfMonth() < 25) {
            LocalDate month = today.withDayOfMonth(1);
            vendorPlanSelectionHistoryRepository.deleteByUserAndSelectedMonth(user, month);
            createPlanHistoryHelper();
            List<VendorPlanSelection> selections = vendorPlanSelectionRepository.findByUserId(user.getId());
            createHistoryHelper(month, selections);
        }
    }

    @Override
    public List<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByUserId(Long userId) {
        List<VendorPlanSelectionHistory> vendorPlanSelectionHistories = vendorPlanSelectionHistoryRepository
                .findByUserId(userId);
        List<VendorPlanSelectionHistorySummaryDto> vendorPlanSelectionHistorySummaryDtos = new ArrayList<>();
        for (VendorPlanSelectionHistory vendorPlanSelectionHistory : vendorPlanSelectionHistories) {
            vendorPlanSelectionHistorySummaryDtos.add(vendorPlanSelectionHistoryMapper.toSummaryDto(vendorPlanSelectionHistory));
        }
        return vendorPlanSelectionHistorySummaryDtos;
    }

    @Override
    public List<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByUserIdAndMonth(Long userId, LocalDate month) {
        List<VendorPlanSelectionHistory> vendorPlanSelectionHistories = vendorPlanSelectionHistoryRepository
                .findByUserIdAndSelectedMonth(userId, month);
        List<VendorPlanSelectionHistorySummaryDto> vendorPlanSelectionHistorySummaryDtos = new ArrayList<>();
        for (VendorPlanSelectionHistory vendorPlanSelectionHistory : vendorPlanSelectionHistories) {
            vendorPlanSelectionHistorySummaryDtos.add(vendorPlanSelectionHistoryMapper.toSummaryDto(vendorPlanSelectionHistory));
        }
        return vendorPlanSelectionHistorySummaryDtos;
    }

    @Override
    public Page<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByMonthAndVendorPlan(LocalDate month, String vendorPlan, Pageable pageable) {
        Sort sort = Sort.by("roomNumber").ascending();

        Page<VendorPlanSelectionHistory> vendorPlanSelectionHistories = vendorPlanSelectionHistoryRepository
                .findBySelectedMonthAndPlan_PlanName(month.withDayOfMonth(1),
                        vendorPlan,
                        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));

        return vendorPlanSelectionHistories.map(vendorPlanSelectionHistoryMapper::toSummaryDto);
    }

    @Override
    public Page<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByMonthAndVendor(LocalDate month, String vendorName, Pageable pageable) {
        Sort sort = Sort.by("plan.planName").ascending()
                .and(Sort.by("roomNumber").ascending());

        Page<VendorPlanSelectionHistory> vendorPlanSelectionHistories = vendorPlanSelectionHistoryRepository
                .findBySelectedMonthAndPlan_VendorName(month.withDayOfMonth(1),
                        vendorName,
                        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));

        return vendorPlanSelectionHistories.map(vendorPlanSelectionHistoryMapper::toSummaryDto);
    }

    @Override
    public Page<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistoryByMonth(LocalDate month, Pageable pageable) {
        Sort sort = Sort.by("plan.vendorName").ascending()
                .and(Sort.by("plan.planName").ascending())
                .and(Sort.by("roomNumber").ascending());

        Page<VendorPlanSelectionHistory> vendorPlanSelectionHistories = vendorPlanSelectionHistoryRepository
                .findBySelectedMonth(month.withDayOfMonth(1), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));

        return vendorPlanSelectionHistories.map(vendorPlanSelectionHistoryMapper::toSummaryDto);
    }

    @Override
    public Page<VendorPlanSelectionHistorySummaryDto> getVendorPlanSelectionHistory(Pageable pageable) {
        Sort sort = Sort.by("selectedMonth").descending()
                .and(Sort.by("plan.vendorName").ascending())
                .and(Sort.by("plan.planName").ascending())
                .and(Sort.by("roomNumber").ascending());

        Page<VendorPlanSelectionHistory> vendorPlanSelectionHistories = vendorPlanSelectionHistoryRepository
                .findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));

        return vendorPlanSelectionHistories.map(vendorPlanSelectionHistoryMapper::toSummaryDto);
    }

    public void createHistoryHelper(LocalDate month, List<VendorPlanSelection> selections) {

        for (VendorPlanSelection selection : selections) {
            VendorPlan plan = selection.getPlan();
            VendorPlanHistory planHistory = vendorPlanHistoryRepository
                    .findByPlanNameAndVendorNameAndFee(
                            plan.getPlanName(),
                            plan.getVendor().getVendorName(),
                            plan.getFee()
                    );

            VendorPlanSelectionHistory history = new VendorPlanSelectionHistory();
            history.setUser(selection.getUser());
            history.setRoomNumber(selection.getRoomNumber());
            history.setSelectedMonth(month);
            history.setPlan(planHistory);
            vendorPlanSelectionHistoryRepository.save(history);
        }
    }

    public void createPlanHistoryHelper() {
        List<VendorPlan> vendorPlans = vendorPlanRepository.findAll();
        for (VendorPlan plan : vendorPlans) {
            boolean exists = vendorPlanHistoryRepository.existsByPlanNameAndVendorNameAndFee(
                    plan.getPlanName(), plan.getVendor().getVendorName(), plan.getFee());
            if (!exists) {
                VendorPlanHistory history = new VendorPlanHistory();
                history.setPlanName(plan.getPlanName());
                history.setVendorName(plan.getVendor().getVendorName());
                history.setMealTypes(new HashSet<>(plan.getMealTypes()));
                history.setFee(plan.getFee());
                vendorPlanHistoryRepository.save(history);
            }
        }
    }
}