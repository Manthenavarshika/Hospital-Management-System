-- ==============================
-- HOSPITAL MANAGEMENT DATABASE
-- ==============================

CREATE DATABASE hospital;
USE hospital;

-- ==============================
-- PATIENTS TABLE
-- ==============================
CREATE TABLE patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL
);

-- ==============================
-- DOCTORS TABLE
-- ==============================
CREATE TABLE doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    Specilization VARCHAR(100) NOT NULL
);

-- ==============================
-- APPOINTMENTS TABLE
-- ==============================
CREATE TABLE appointments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patients_id INT,
    doctor_id INT,
    appointment_date DATE,
    
    FOREIGN KEY (patients_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

-- ==============================
-- SAMPLE DATA (IMPORTANT FOR DEMO)
-- ==============================

INSERT INTO doctors(name, Specilization) VALUES
('Dr. Ramesh','Cardiologist'),
('Dr. Priya','Dermatologist'),
('Dr. Kiran','Orthopedic'),
('Dr. Suresh','Neurologist');

INSERT INTO patients(name,age,gender) VALUES
('Anil',25,'Male'),
('Meena',30,'Female');