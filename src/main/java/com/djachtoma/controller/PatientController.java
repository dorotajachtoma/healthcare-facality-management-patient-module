package com.djachtoma.controller;

import com.djachtoma.model.patient.dto.PatientDTO;
import com.djachtoma.model.patient.dto.PatientFilter;
import com.djachtoma.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public TreeSet<PatientDTO> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping
    public PatientDTO getPatient(@RequestParam PatientFilter patientFilter) {
        return patientService.getPatient(patientFilter);
    }

    @PostMapping
    public PatientDTO createPatient(@RequestParam PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @PatchMapping
    public PatientDTO updatePatient(@RequestParam String id, @RequestParam PatientDTO patientDTO) {
        return patientService.updatePatient(id, patientDTO);
    }

    @DeleteMapping
    public void deletePatient(@RequestParam String id) {
        patientService.deletePatient(id);
    }
}
