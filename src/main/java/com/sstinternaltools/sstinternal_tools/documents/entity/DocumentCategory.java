package com.sstinternaltools.sstinternal_tools.documents.entity;

import jakarta.persistence.*;

@Entity
public class DocumentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

}