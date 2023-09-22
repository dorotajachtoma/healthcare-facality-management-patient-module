package com.djachtoma.controller;

import com.djachtoma.model.patient.dto.PatientDTO;
import com.djachtoma.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public Flux<PatientDTO> getPatients() {
        log.info("%s is invoked.", this.getClass().getName() + ".getPatients");
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public Mono<PatientDTO> getPatient(@PathVariable String id) {
        log.info("%s is invoked.", this.getClass().getName() + ".getPatient");
        return patientService.getPatient(id);
    }

    @PostMapping
    public Mono<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        log.info("%s is invoked.", this.getClass().getName() + ".createPatient");
        return patientService.createPatient(patientDTO);
    }

    @PatchMapping("/{id}")
    public Mono<PatientDTO> updatePatient(@PathVariable String id, @RequestBody PatientDTO patientDTO) {
        log.info("%s is invoked.", this.getClass().getName() + ".updatePatient");
        return patientService.updatePatient(id, patientDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        log.info("%s is invoked.", this.getClass().getName() + ".deletePatient");
        patientService.deletePatient(id);
    }
}
