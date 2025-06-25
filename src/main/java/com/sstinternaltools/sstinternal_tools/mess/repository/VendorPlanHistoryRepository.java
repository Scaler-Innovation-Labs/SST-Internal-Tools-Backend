package com.sstinternaltools.sstinternal_tools.mess.repository;

import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VendorPlanHistoryRepository extends JpaRepository<VendorPlanHistory, Long> {
    boolean existsByPlanNameAndVendorNameAndFee(String planName, String vendorName, Double fee);
    VendorPlanHistory findByPlanNameAndVendorNameAndFee(String planName, String vendorName, Double fee);
}

