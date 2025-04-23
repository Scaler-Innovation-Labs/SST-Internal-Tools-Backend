package com.sstinternaltools.sstinternal_tools.mess.entity;

import jakarta.persistence.*;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id",nullable = false)
    private Long id;
    private String name;
}
