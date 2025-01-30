package com.hospital.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.demo.repository.AuditLogRepository;
import com.hospital.demo.entity.AuditLog;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

  @Autowired
  private AuditLogRepository auditLogRepository;

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<AuditLog> getLogs() {
    return auditLogRepository.findAll();
  }
}