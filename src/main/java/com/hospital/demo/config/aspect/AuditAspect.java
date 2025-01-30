package com.hospital.demo.config.aspect;

import com.hospital.demo.entity.AuditLog;
import com.hospital.demo.repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @AfterReturning(
        pointcut = "execution(* com.example.prms.service.*.*(..))",
        returning = "result"
    )
    public void logAction(JoinPoint joinPoint, Object result) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String action = joinPoint.getSignature().getName();
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setUsername(username);
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }
}
