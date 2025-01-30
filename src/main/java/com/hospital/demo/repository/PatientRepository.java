package com.hospital.demo.repository;

import com.hospital.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    //Use Spring Data JPA for database operations.
}
