package com.hospital.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmailService {
  public static void send(String to, String message) {
    // Implement SendGrid API logic here
  }
}