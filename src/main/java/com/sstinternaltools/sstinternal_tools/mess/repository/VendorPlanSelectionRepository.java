package com.sstinternaltools.sstinternal_tools.mess.repository;

import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorPlanSelectionRepository extends JpaRepository<VendorPlanSelection, Long> {
    List<VendorPlanSelection> findByUserIdAndPlanId (Long userId, Long vendorId);
    List<VendorPlanSelection> findByUserId(Long userId);
    Boolean existsByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    @Query("SELECT s FROM VendorPlanSelection s " +
            "JOIN FETCH s.user u " +
            "JOIN FETCH s.plan p " +
            "JOIN FETCH p.vendor v")
    List<VendorPlanSelection> findAllWithDetails();
}
