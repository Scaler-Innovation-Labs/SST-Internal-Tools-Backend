package com.sstinternaltools.sstinternal_tools.mess.entity;

import jakarta.persistence.*;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id",nullable = false)
    private Long id;
    private String vendorName;

    public Vendor() {}
    public Vendor(String name) {
        this.vendorName = name;
    }

    public Long getId() {
        return id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String name) {
        this.vendorName = name;
    }
}
