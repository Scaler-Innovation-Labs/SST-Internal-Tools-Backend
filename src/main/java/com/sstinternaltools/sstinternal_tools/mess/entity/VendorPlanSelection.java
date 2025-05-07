package com.sstinternaltools.sstinternal_tools.mess.entity;

import com.sstinternaltools.sstinternal_tools.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.YearMonth;

@Entity
public class VendorPlanSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="vendor_plan_id")
    private VendorPlan plan;

    private LocalDate selectedMonth;

    public VendorPlanSelection() {}

    public VendorPlanSelection(User user, LocalDate selectedMonth, VendorPlan plan) {
        this.user = user;
        this.selectedMonth = LocalDate.of(selectedMonth.getYear(), selectedMonth.getMonth(), 1);
        this.plan = plan;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VendorPlan getPlan() {
        return plan;
    }

    public void setPlan(VendorPlan plan) {
        this.plan = plan;
    }

    public LocalDate getMonth() {
        return selectedMonth;
    }

    public void setMonth(LocalDate selectedMonth) {
        this.selectedMonth = selectedMonth;
    }
}
