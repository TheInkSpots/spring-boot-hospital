package com.hospital.demo.service;


import com.hospital.demo.entity.Patient;
import com.hospital.demo.repository.PatientRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    // Create a new patient record
    @Transactional
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public List<Patient> createPatients(List<Patient> patients) {
        return patientRepository.saveAll(patients);
    }

    // Retrieve a patient by ID
    @Transactional(readOnly = true)
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
    }


    // Lock a record
    @Transactional
    public boolean lockRecord(Long id, String username) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        if (patient.getLockedBy() != null) {
            return false; // Already locked
        }
        patient.setLockedBy(username);
        patientRepository.save(patient);
        return true;
    }

    // Unlock a record
    @Transactional
    public void unlockRecord(Long id, String username) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        if (username.equals(patient.getLockedBy())) {
            patient.setLockedBy(null);
            patientRepository.save(patient);
        }
    }

    // Update a record (only if unlocked or locked by the same user)
    @Transactional
    public Patient updatePatient(Long id, Patient updatedPatient, String username) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        if (patient.getLockedBy() != null && !patient.getLockedBy().equals(username)) {
            throw new RuntimeException("Record is locked by another user");
        }
        patient.setName(updatedPatient.getName());
        patient.setAge(updatedPatient.getAge());
        patient.setMedicalHistory(updatedPatient.getMedicalHistory());
        return patientRepository.save(patient);
    }
}
