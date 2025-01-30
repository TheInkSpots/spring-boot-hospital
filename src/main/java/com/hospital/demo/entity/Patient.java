package com.hospital.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String medicalHistory;
    private String lockedBy;
}