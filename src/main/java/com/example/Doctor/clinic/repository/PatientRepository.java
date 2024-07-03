package com.example.Doctor.clinic.repository;

import com.example.Doctor.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
