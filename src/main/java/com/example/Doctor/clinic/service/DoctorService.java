package com.example.Doctor.clinic.service;

import com.example.Doctor.clinic.entity.Doctor;
import com.example.Doctor.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void removeDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> suggestDoctors(String city, String symptom) {
        String speciality = getSpecialityFromSymptom(symptom);
        return doctorRepository.findByCityAndSpeciality(city, speciality);
    }

    private String getSpecialityFromSymptom(String symptom) {
        switch (symptom) {
            case "Arthritis":
            case "Back Pain":
            case "Tissue injuries":
                return "Orthopaedic";
            case "Dysmenorrhea":
                return "Gynecology";
            case "Skin infection":
            case "Skin burn":
                return "Dermatology";
            case "Ear pain":
                return "ENT";
            default:
                throw new IllegalArgumentException("Unknown symptom");
        }
    }
}

