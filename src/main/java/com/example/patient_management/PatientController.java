package com.example.patient_management;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class PatientController {

    List<Patient> patients = new ArrayList<>();
    int idCounter = 1;

    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient) {
        patient.setId(idCounter++);
        patients.add(patient);
        return patient;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patients;
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable int id,
                                 @RequestBody Patient updatedPatient) {

        for (Patient p : patients) {
            if (p.getId() == id) {
                p.setName(updatedPatient.getName());
                p.setDisease(updatedPatient.getDisease());
                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/patients/{id}")
    public String deletePatient(@PathVariable int id) {
        patients.removeIf(p -> p.getId() == id);
        return "Patient deleted";
    }
}