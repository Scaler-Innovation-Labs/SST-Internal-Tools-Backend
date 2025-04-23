package com.sstinternaltools.sstinternal_tools.mess.repository;

import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorPlanSelectionRepository extends JpaRepository<VendorPlanSelection, Integer> {
}
