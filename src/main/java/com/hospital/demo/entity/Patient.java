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
    private String email;
    private String medicalHistory;

    @Encrypted
    private String hkid;

    @Column(nullable = true)
    private String lockedBy;

    public String getEmail() {
        return this.email;
    }
}