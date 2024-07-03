package com.example.Doctor.clinic.repository;

import com.example.Doctor.clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByCityAndSpeciality(String city, String speciality);
}