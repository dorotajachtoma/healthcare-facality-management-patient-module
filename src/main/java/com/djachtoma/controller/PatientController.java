package com.djachtoma.controller;

import com.djachtoma.model.patient.dto.PatientDTO;
import com.djachtoma.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.TreeSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public Flux<PatientDTO> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public Mono<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @PatchMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable String id, @RequestBody PatientDTO patientDTO) {
        return patientService.updatePatient(id, patientDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }
}
