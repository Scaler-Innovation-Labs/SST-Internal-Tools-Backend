package com.sstinternaltools.sstinternal_tools.mess.repository;

import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorPlanRepository extends JpaRepository<VendorPlan, Long> {
    List<VendorPlan> findByVendorId(Long vendorId);
    List<VendorPlan> findByVendorIdAndMealTypesContaining(Long vendorId, MealType mealType);
}
