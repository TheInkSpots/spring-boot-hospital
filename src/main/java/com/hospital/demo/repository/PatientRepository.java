package com.hospital.demo.repository;

import com.hospital.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface PatientRepository extends JpaRepository<Patient, Long> {

    //Use Spring Data JPA for database operations.
}
