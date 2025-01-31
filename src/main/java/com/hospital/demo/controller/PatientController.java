package com.hospital.demo.controller;

import com.hospital.demo.entity.Patient;
import com.hospital.demo.service.PatientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Patient>> createPatients(@RequestBody List<Patient> patients) {
        List<Patient> createdPatients = patientService.createPatients(patients);
        return ResponseEntity.ok(createdPatients);
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return patientService.updatePatient(id, updatedPatient, username);
    }

    @PostMapping("/{id}/lock")
    public ResponseEntity<String> lockRecord(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean locked = patientService.lockRecord(id, username);
        if (locked) {
            return ResponseEntity.ok("Record locked by " + username);
        } else {
            return ResponseEntity.badRequest().body("Record is already locked");
        }
    }

    @PostMapping("/{id}/unlock")
    public ResponseEntity<String> unlockRecord(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        patientService.unlockRecord(id, username);
        return ResponseEntity.ok("Record unlocked");
    }
}
