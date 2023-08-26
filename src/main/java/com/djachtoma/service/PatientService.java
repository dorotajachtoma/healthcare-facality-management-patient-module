package com.djachtoma.service;

import com.djachtoma.exception.ItemNotFoundException;
import com.djachtoma.model.patient.Patient;
import com.djachtoma.model.patient.dto.PatientDTO;
import com.djachtoma.model.patient.dto.PatientMapper;
import com.djachtoma.repository.PatientRepository;
import com.djachtoma.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        patientRepository.save(patient);
        return patientMapper.toDTO(patient);
    }

    @Transactional
    public void deletePatient(String id) {
        Patient patient = getPatient(id);
        patientRepository.delete(patient);
    }

    @Transactional
    public PatientDTO updatePatient(String id, PatientDTO patientDTO) {
        Patient patient = getPatient(id);
        update(patient, patientDTO);
        patientRepository.save(patient);

        return patientMapper.toDTO(patient);
    }


    private Patient getPatient(String id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Patient with provided ID: %s does not exit.", id)));
    }

    private Patient update(Patient patient, PatientDTO patientDTO) {
        ObjectUtils.nullSafeUpdate(patientDTO.getName(), x -> patientDTO.getName(), () -> patient.setName(x));
        if(Objects.nonNull(patientDTO.getName())) {
            patient.setName(patientDTO.getName());
        }
    }
}
