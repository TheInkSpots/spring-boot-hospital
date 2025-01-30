package com.hospital.demo.service;

import com.hospital.demo.entity.Appointment;
import com.hospital.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Scheduled(cron = "0 0 8 * * ?") // Run daily at 8 AM
  public void sendDailyReminders() {
    List<Appointment> appointments = appointmentRepository.findByDate(LocalDate.now().plusDays(1));
    appointments.forEach(appointment -> {
      String message = String.format(
        "Reminder: Your appointment is scheduled for %s",
        appointment.getDateTime()
      );
      // Send email/SMS using a service like SendGrid or Twilio
      EmailService.send(appointment.getPatient().getEmail(), message);
    });
  }
}


