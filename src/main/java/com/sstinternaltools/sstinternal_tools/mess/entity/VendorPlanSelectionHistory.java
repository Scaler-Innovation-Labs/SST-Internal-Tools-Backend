package com.sstinternaltools.sstinternal_tools.mess.entity;

import com.sstinternaltools.sstinternal_tools.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class VendorPlanSelectionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vendor_plan_selection_history_id",nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="vendor_plan_history_id")
    private VendorPlanHistory plan;

    private LocalDate selectedMonth;
    private Integer roomNumber;

    public VendorPlanSelectionHistory() {}

    public VendorPlanSelectionHistory(Long id, User user, VendorPlanHistory plan, LocalDate selectedMonth, Integer roomNumber) {
        this.id = id;
        this.user = user;
        this.plan = plan;
        this.selectedMonth = selectedMonth;
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(LocalDate selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public VendorPlanHistory getPlan() {
        return plan;
    }

    public void setPlan(VendorPlanHistory plan) {
        this.plan = plan;
    }
}
