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

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public Flux<PatientDTO> getPatients(Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".getPatients", principal.getName());
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public Mono<PatientDTO> getPatient(@PathVariable String id, Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".getPatient", principal.getName());
        return patientService.getPatient(id);
    }

    @PostMapping
    public Mono<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO, Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".createPatient", principal.getName());
        return patientService.createPatient(patientDTO);
    }

    @PatchMapping("/{id}")
    public Mono<PatientDTO> updatePatient(@PathVariable String id, @RequestBody PatientDTO patientDTO,
                                          Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".updatePatient", principal.getName());
        return patientService.updatePatient(id, patientDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id, Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".deletePatient", principal.getName());
        patientService.deletePatient(id);
    }
}
