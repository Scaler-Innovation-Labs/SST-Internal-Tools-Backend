package com.sstinternaltools.sstinternal_tools.mess.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class VendorPlanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_plan_history_id",nullable = false)
    private Long id;
    private String vendorName;
    private String planName;
    private Double fee;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<MealType> mealTypes;

    public VendorPlanHistory() {}

    public VendorPlanHistory(Long id, String vendorName, String planName, Double fee, Set<MealType> mealTypes) {
        this.id = id;
        this.vendorName = vendorName;
        this.planName = planName;
        this.fee = fee;
        this.mealTypes = mealTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Set<MealType> getMealTypes() {
        return mealTypes;
    }

    public void setMealTypes(Set<MealType> mealTypes) {
        this.mealTypes = mealTypes;
    }
}
