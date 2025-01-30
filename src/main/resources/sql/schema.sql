CREATE TABLE roles (
  id BIGINT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE -- e.g., "NURSE", "DOCTOR"
);

CREATE TABLE users (
  id BIGINT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role_id BIGINT REFERENCES roles(id)
);

CREATE TABLE patients (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  age INT NOT NULL,
  medical_history TEXT,
  hkid VARCHAR(255) NOT NULL
);

CREATE TABLE audit_logs (
  id BIGINT PRIMARY KEY,
  action VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  details TEXT,
  timestamp TIMESTAMP NOT NULL
);

CREATE TABLE appointments (
  id BIGINT PRIMARY KEY,
  patient_id BIGINT REFERENCES patients(id),
  date_time TIMESTAMP NOT NULL,
  status VARCHAR(50) NOT NULL -- e.g., "SCHEDULED", "COMPLETED"
);

