package com.sstinternaltools.sstinternal_tools.mess.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class VendorPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_plan_id",nullable = false)
    private Long id;
    private String planName;

    @ManyToOne
    @JoinColumn(name="vendor_id")
    private Vendor vendor;
    private Double fee;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<MealType> mealTypes;

    public VendorPlan() {}

    public VendorPlan(String planName, Vendor vendor, Double fee, Set<MealType> mealTypes) {
        this.planName = planName;
        this.vendor = vendor;
        this.fee = fee;
        this.mealTypes = mealTypes;
    }

    public Set<MealType> getMealTypes() {
        return mealTypes;
    }

    public void setMealTypes(Set<MealType> mealTypes) {
        this.mealTypes = mealTypes;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Long getId() {
        return id;
    }

}
