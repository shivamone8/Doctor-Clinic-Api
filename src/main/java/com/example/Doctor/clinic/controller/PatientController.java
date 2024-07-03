package com.example.Doctor.clinic.controller;

import com.example.Doctor.clinic.entity.Doctor;
import com.example.Doctor.clinic.entity.Patient;
import com.example.Doctor.clinic.service.DoctorService;
import com.example.Doctor.clinic.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public Patient addPatient(@RequestBody @Valid Patient patient) {
        return patientService.addPatient(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePatient(@PathVariable Long id) {
        patientService.removePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/suggest/{id}")
    public ResponseEntity<?> suggestDoctors(@PathVariable Long id) {
        Optional<Patient> patientOpt = patientService.getPatientById(id);
        if (!patientOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        Patient patient = patientOpt.get();
        List<Doctor> doctors = doctorService.suggestDoctors(patient.getCity(), patient.getSymptom());
        if (doctors.isEmpty()) {
            if (!patient.getCity().matches("Delhi|Noida|Faridabad")) {
                return ResponseEntity.ok("We are still waiting to expand to your location");
            } else {
                return ResponseEntity.ok("There isn’t any doctor present at your location for your symptom");
            }
        }
        return ResponseEntity.ok(doctors);
    }
}
