package com.hospital.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action; // e.g., "CREATE_PATIENT", "UPDATE_RECORD"
    private String username;
    private LocalDateTime timestamp;
}