package com.hospital.demo.repository;

import com.hospital.demo.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Fetch appointments scheduled for a specific date (e.g., tomorrow)
    @Query("SELECT a FROM Appointment a WHERE DATE(a.dateTime) = :date")
    List<Appointment> findByDate(@Param("date") LocalDate date);
}