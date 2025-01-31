-- Create roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_NURSE'), ('ROLE_DOCTOR');

-- Create an admin user (password: "admin" encrypted with BCrypt)
INSERT INTO users (username, password, role_id) 
VALUES ('admin', '$2a$12$boNVIi3NrtvFBwLepe3TXubASgVjf/.QOR1NYFpRSel0WiuldXk3q', 1);

INSERT INTO patients (name, age, medical_history) VALUES 
('John Doe', 30, 'Allergic to penicillin'),
('Jane Smith', 45, 'Hypertension');

INSERT INTO appointments (patient_id, date_time, status) VALUES 
(1, '2024-05-20 14:30:00', 'SCHEDULED'),
(2, '2024-05-21 10:00:00', 'SCHEDULED');