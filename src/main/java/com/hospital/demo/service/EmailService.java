package com.hospital.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmailService {
  public static void send(String to, String message) {
    // Implement SendGrid API logic here

     // Replace with actual email logic (e.g., SendGrid, JavaMail)
     System.out.println("Sending email to: " + to);
     System.out.println("Message: " + message);
     
  }
}