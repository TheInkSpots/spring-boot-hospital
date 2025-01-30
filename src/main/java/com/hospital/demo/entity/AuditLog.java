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
    private String details;
    private LocalDateTime timestamp;

    public  void setAction(String str){


    }

    public  void setUsername(String str){
        

    }

    public  void setTimestamp(LocalDateTime time){
        

    }
}