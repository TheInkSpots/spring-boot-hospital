package com.hospital.demo.repository;

import com.hospital.demo.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    //Use Spring Data JPA for database operations.
    
}