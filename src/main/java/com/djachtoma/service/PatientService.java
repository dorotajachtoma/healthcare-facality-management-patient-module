package com.djachtoma.service;

import com.djachtoma.exception.PatientNotFoundException;
import com.djachtoma.model.patient.Patient;
import com.djachtoma.model.patient.dto.PatientDTO;
import com.djachtoma.model.patient.dto.PatientMapper;
import com.djachtoma.reference.entity.model.Gender;
import com.djachtoma.reference.entity.model.IDCard;
import com.djachtoma.reference.entity.model.PhoneNumber;
import com.djachtoma.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.djachtoma.utils.ObjectUtils.nullSafeUpdate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public Flux<PatientDTO> getPatients() {
        Iterable<PatientDTO> patients = Stream.of(patientRepository.findAll().iterator())
                        .map(patient -> patientMapper.toDTO(patient.next()))
                        .collect(Collectors.toList());
        return Flux.fromIterable(patients);
    }

    public Mono<PatientDTO> getPatient(String id) {
        return Mono.just(patientMapper.toDTO(getPatientById(id)));
    }

    @Transactional
    public Mono<PatientDTO> createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        patientRepository.save(patient);
        return Mono.just(patientMapper.toDTO(patient));
    }

    @Transactional
    public void deletePatient(String id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }

    @Transactional
    public Mono<PatientDTO> updatePatient(String id, PatientDTO patientDTO) {
        Patient patient = getPatientById(id);
        updatePatientEntity(patient, patientDTO);
        return Mono.just(patientMapper.toDTO(patient));
    }


    private Patient getPatientById(String id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(String.format("Patient with provided ID: %s does not exist.", id)));
    }

    private void updatePatientEntity(Patient patient, PatientDTO patientDTO) {
        nullSafeUpdate(patientDTO.getName(), patientDTO::getName, patient::setName);
        nullSafeUpdate(patientDTO.getSurname(), patientDTO::getSurname, patient::setName);
        nullSafeUpdate(patientDTO.getGender(), patientDTO::getGender, x -> patient.setGender(Gender.valueOf(x)));
        nullSafeUpdate(patient.getDateOfBirth(), patientDTO::getDateOfBirth, patient::setDateOfBirth);
        nullSafeUpdate(patientDTO.getIdCardSeriesNumber(), patientDTO::getIdCardSeriesNumber, x -> patient.setIdCard(IDCard.builder()
                .seriesNumber(x)
                .build()));
        nullSafeUpdate(patientDTO.getPhoneNumber(), patientDTO::getPhoneNumber, x -> patient.setPhoneNumber(PhoneNumber.builder()
                .number(x)
                .build()));
        patientRepository.save(patient);
    }
}
